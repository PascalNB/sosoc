package nl.utwente.sosoc.playbookmanagement.service;

import jakarta.annotation.PostConstruct;
import nl.utwente.sosoc.playbookmanagement.entity.PlaybookEntity;
import nl.utwente.sosoc.playbookmanagement.producers.WorkflowsProducer;
import nl.utwente.sosoc.playbookmanagement.model.*;
import nl.utwente.sosoc.playbookmanagement.repository.PlaybookRepository;
import nl.utwente.sosoc.playbookmanagement.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaybookManagementService {

    @Autowired private WorkflowsProducer workflowsProducer;
    @Autowired private PlaybookRepository playbookRepository;
    @Autowired private EntityMapper entityMapper;

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
        playbookRepository.save(entityMapper.to(PlaybookEntity.class).apply(new Playbook()
            .id(uuid)
            .name("Phishing Email Playbook")
            .description("Block email and then notify user")
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
            .trigger("Threat.Code == 'phishing'")
        ));
    }

    public Playbook detectPlaybook(Alarm alarm) {
        ExpressionParser parser = new SpelExpressionParser();
        for (PlaybookEntity playbookEntity : playbookRepository.findAll()) {
            Playbook playbook = entityMapper.to(Playbook.class).apply(playbookEntity);
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

}
