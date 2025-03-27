package nl.utwente.sosoc.playbookmanagement;

import jakarta.annotation.PostConstruct;
import nl.utwente.sosoc.playbookmanagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaybookManagementService {
    private static Map<UUID, Playbook> playbooks = new HashMap<>();

    @Autowired private WorkflowsProducer workflowsProducer;

    private Optional<Step> getStep(Playbook playbook, UUID stepId) {
        return Objects.requireNonNull(playbook)
                .getSteps().stream()
                .filter(step -> Objects.equals(stepId, step.getId()))
                .findFirst();
    }

    @PostConstruct
    public void init() {
        UUID uuid = UUID.randomUUID();
        UUID firstStepId = UUID.randomUUID();
        UUID nextStepId = UUID.randomUUID();
        Playbook playbook = new Playbook()
                .id(uuid)
                .name("Phishing Email Playbook")
                .firstStep(firstStepId)
                .steps(List.of(
                        new Step()
                                .id(firstStepId)
                                .name("Block Email")
                                .action("block email")
                                .type(Step.TypeEnum.AUTO)
                                .next(List.of(
                                        new ConditionalNext()
                                                .condition(Severity.HIGH.getValue())
                                                .next(nextStepId)
                                )),
                        new Step()
                                .id(nextStepId)
                                .name("Notify User")
                                .action("notify user")
                                .type(Step.TypeEnum.HUMAN)
                ))
                .trigger("Threat.Code == 'phishing'");
        playbooks.put(uuid, playbook);
    }

    public Playbook detectPlaybook(Alarm alarm) {
        ExpressionParser parser = new SpelExpressionParser();
        for (Playbook playbook : playbooks.values()) {
            String trigger = playbook.getTrigger();
            if (trigger == null) {
                continue;
            }
            Expression expression = parser.parseExpression(trigger);
            try {
                Boolean result = expression.getValue(alarm, Boolean.class);
                if (result != null && result) {
                    return playbook;
                }
            } catch (EvaluationException ignored) {
            }
        }
        return null;
    }

    public void consumeAlarm(Alarm alarm) {
        Playbook playbook = detectPlaybook(alarm);
        if (playbook != null) {
            System.out.println("Triggering playbook: " + playbook.getName());
            Workflow workflow = new Workflow()
                    .id(UUID.randomUUID())
                    .playbook(playbook)
                    .alarm(alarm)
                    .nextStep(playbook.getFirstStep());
            Step nextStep = getStep(playbook, playbook.getFirstStep()).orElseThrow();
            if (Step.TypeEnum.AUTO.equals(nextStep.getType())) {
                workflowsProducer.send("workflow.auto", workflow);

            } else if (Step.TypeEnum.HUMAN.equals(nextStep.getType())) {
                workflowsProducer.send("workflow.human", workflow);
            }
        }
    }
    /**
     * Get a playbook by its ID.
     * @param id the ID of the playbook
     * @return the playbook with the given ID
     */
    public Playbook getPlaybookById(UUID id) {
        return playbooks.get(id);
    }

    /**
     * Get all playbooks.
     * @return a list of all playbooks.
     */
    public List<Playbook> getPlaybooks() {
        return playbooks.values().stream().toList();
    }

    /**
     * Save a new playbook with a randomID.
     * @param playbook the playbook to save.
     */
    public void savePlaybook(Playbook playbook) {
        UUID newId = UUID.randomUUID();
        playbook.setId(newId);
        playbooks.put(newId, playbook);
    }

    /**
     * Remove a given playbook.
     * @param id of the playbook to remove.
     */
    public void deletePlaybook(UUID id) {
        playbooks.remove(id);
    }
}
