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
import java.util.stream.Collectors;

@Service
public class PlaybookManagementService {

    @Autowired private WorkflowsProducer workflowsProducer;
    @Autowired private PlaybookRepository playbookRepository;
    @Autowired private EntityMapper entityMapper;

    @PostConstruct
    public void init() {
        playbookRepository.save(entityMapper.to(PlaybookEntity.class).apply(new Playbook()
            .name("Phishing Email Playbook")
            .description("Block email and then notify user")
            .firstStep("Block Email")
            .steps(List.of(
                new Step()
                    .name("Block Email")
                    .action("block email")
                    .type(Step.TypeEnum.AUTO)
                    .next(List.of(
                        new ConditionalNext()
                            .condition(Severity.HIGH.getValue())
                            .name("Notify Analysts"),
                        new ConditionalNext()
                            .condition("default")
                            .name("Notify Analysts")
                    )),
                new Step()
                    .name("Notify Analysts")
                    .action("notify analyst")
                    .type(Step.TypeEnum.HUMAN)
            ))
            .trigger("Threat.Code == 'phishing'")
        ));
    }

    private Map<String, Step> getSteps(Playbook playbook) {
        return playbook.getSteps().stream()
            .collect(Collectors.toMap(Step::getName, step -> step));
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
                    System.out.println("Triggering playbook: " + playbook.getName());
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
            Workflow workflow = new Workflow()
                .id(UUID.randomUUID())
                .playbook(playbook)
                .alarm(alarm)
                .nextStep(playbook.getFirstStep());
            System.out.println("Creating workflow: " + workflow.getId());
            Map<String, Step> steps = getSteps(playbook);
            Step nextStep = steps.get(playbook.getFirstStep());

            if (Step.TypeEnum.AUTO.equals(nextStep.getType())) {
                workflowsProducer.send("workflow.auto", workflow);
            } else if (Step.TypeEnum.HUMAN.equals(nextStep.getType())) {
                workflowsProducer.send("workflow.human", workflow);
            }
        }
    }

}
