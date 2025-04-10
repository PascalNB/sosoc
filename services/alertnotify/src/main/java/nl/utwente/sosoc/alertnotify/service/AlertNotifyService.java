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

    private Map<UUID, Step> getSteps(Workflow workflow) {
        return Objects.requireNonNull(workflow.getPlaybook()).getSteps().stream()
            .collect(Collectors.toMap(Step::getId, step -> step));
    }

    public void executeWorkflow(Workflow workflow) {
        UUID nextStepId = workflow.getNextStep();
        Map<UUID, Step> steps = getSteps(workflow);
        Step currentStep = steps.get(nextStepId);
        if (currentStep == null) {
            return;
        }
        List<ConditionalNext> nextSteps = currentStep.getNext();
        String[] action = currentStep.getAction() == null ? null : currentStep.getAction().split(" +");
        System.out.println("Execute dummy action: " + Arrays.toString(action));

        executeAction(action, workflow.getAlarm());

        if (nextSteps.isEmpty()) {
            System.out.println("Workflow " + workflow.getId() + " ended");
            return;
        }

        // no conditions for human action
        Step nextStep = steps.get(nextSteps.get(0).getNext());
        workflow.setNextStep(nextStep.getId());

        if (Step.TypeEnum.AUTO.equals(nextStep.getType())) {
            workflowsProducer.send("workflow.auto", workflow);

        } else if (Step.TypeEnum.HUMAN.equals(nextStep.getType())) {
            workflowsProducer.send("workflow.human", workflow);
        }
    }

    public void executeAction(String[] action, Alarm alarm) {
        if (action == null || action.length == 0) {
            return;
        }
        switch (action[0]) {
            case "notify" -> actionExecutorService.notify(action, alarm);
            default -> {}
        }
    }

}
