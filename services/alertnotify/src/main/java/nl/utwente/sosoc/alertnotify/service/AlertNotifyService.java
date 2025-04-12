package nl.utwente.sosoc.alertnotify.service;

import nl.utwente.sosoc.alertnotify.model.Alarm;
import nl.utwente.sosoc.alertnotify.producers.WorkflowsProducer;
import nl.utwente.sosoc.alertnotify.model.ConditionalNext;
import nl.utwente.sosoc.alertnotify.model.Step;
import nl.utwente.sosoc.alertnotify.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlertNotifyService {

    @Autowired private WorkflowsProducer workflowsProducer;
    @Autowired private ActionExecutorService actionExecutorService;

    private Map<String, Step> getSteps(Workflow workflow) {
        return Objects.requireNonNull(workflow.getPlaybook()).getSteps().stream()
            .collect(Collectors.toMap(Step::getName, step -> step));
    }

    public void executeWorkflow(Workflow workflow) {
        String nextStepName = workflow.getNextStep();
        Map<String, Step> steps = getSteps(workflow);
        Step currentStep = steps.get(nextStepName);
        if (currentStep == null) {
            return;
        }
        List<ConditionalNext> nextSteps = currentStep.getNext();
        String[] action = currentStep.getAction() == null ? null : currentStep.getAction().split(" +");
        System.out.println("Execute action: " + Arrays.toString(action));

        actionExecutorService.execute(action, workflow.getAlarm());

        if (nextSteps.isEmpty()) {
            System.out.println("Workflow " + workflow.getId() + " ended");
            return;
        }

        // no conditions for human action
        Step nextStep = steps.get(nextSteps.getFirst().getName());
        workflow.setNextStep(nextStep.getName());

        if (Step.TypeEnum.AUTO.equals(nextStep.getType())) {
            workflowsProducer.send("workflow.auto", workflow);

        } else if (Step.TypeEnum.HUMAN.equals(nextStep.getType())) {
            workflowsProducer.send("workflow.human", workflow);
        }
    }

}
