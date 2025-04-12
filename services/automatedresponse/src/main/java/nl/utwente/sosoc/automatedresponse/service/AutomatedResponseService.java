package nl.utwente.sosoc.automatedresponse.service;

import nl.utwente.sosoc.automatedresponse.producers.WorkflowsProducer;
import nl.utwente.sosoc.automatedresponse.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AutomatedResponseService {

    @Autowired private WorkflowsProducer workflowsProducer;

    private Map<String, Step> getSteps(Workflow workflow) {
        return Objects.requireNonNull(workflow.getPlaybook()).getSteps().stream()
            .collect(Collectors.toMap(Step::getName, step -> step));
    }

    public void execute(Workflow workflow) {
        String currentStepName = workflow.getNextStep();
        Map<String, Step> steps = getSteps(workflow);
        Step step = steps.get(currentStepName);
        if (step == null) {
            return;
        }
        List<ConditionalNext> nextSteps = step.getNext();

        String action = step.getAction();
        System.out.println("Execute dummy action: " + action);
        // TODO: execute automatic step

        if (nextSteps.isEmpty()) {
            System.out.println("Workflow " + workflow.getId() + " ended");
            return;
        }

        Step nextStep;
        if (nextSteps.size() == 1) {
            nextStep = steps.get(nextSteps.getFirst().getName());
        } else {
            Severity severity = workflow.getAlarm().getThreat().getSeverity();
            // find step that matches severity
            Optional<String> candidateStepName = nextSteps.stream()
                .filter(conditionalStep ->
                    severity.equals(Severity.fromValue(conditionalStep.getCondition()))
                )
                .map(ConditionalNext::getName)
                .findFirst();
            if (candidateStepName.isPresent()) {
                nextStep = steps.get(candidateStepName.get());
            } else {
                // find default step
                nextStep = nextSteps.stream()
                    .filter(conditionalStep -> "default".equals(conditionalStep.getCondition()))
                    .map(ConditionalNext::getName)
                    .findFirst()
                    .map(steps::get)
                    .orElse(null);
            }
        }

        if (nextStep == null) {
            System.out.println("Workflow " + workflow.getId() + " ended: next step not found");
            return;
        }

        workflow.nextStep(nextStep.getName());

        if (Step.TypeEnum.AUTO.equals(nextStep.getType())) {
            workflowsProducer.send("workflow.auto", workflow);

        } else if (Step.TypeEnum.HUMAN.equals(nextStep.getType())) {
            workflowsProducer.send("workflow.human", workflow);
        }
    }

}
