package nl.utwente.sosoc.alertnotify;

import nl.utwente.sosoc.alertnotify.model.ConditionalNext;
import nl.utwente.sosoc.alertnotify.model.Step;
import nl.utwente.sosoc.alertnotify.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlertNotifyService {

    @Autowired private WorkflowsProducer workflowsProducer;

    private Optional<Step> getStep(Workflow workflow, UUID stepId) {
        return Objects.requireNonNull(workflow.getPlaybook())
            .getSteps().stream()
            .filter(step -> Objects.equals(stepId, step.getId()))
            .findFirst();
    }

    public void execute(Workflow workflow) {
        UUID nextStepId = workflow.getNextStep();
        Optional<Step> currentStep = getStep(workflow, nextStepId);
        if (currentStep.isEmpty()) {
            return;
        }
        Step step = currentStep.get();
        List<ConditionalNext> nextSteps = step.getNext();

        String action = step.getAction();
        System.out.println("Execute action: " + action);
        // TODO: execute manual step

        if (nextSteps.isEmpty()) {
            System.out.println("Workflow " + workflow.getId() + " ended");
            return;
        }

        Step nextStep;
        if (nextSteps.size() == 1) {
            nextStep = getStep(workflow, nextSteps.get(0).getNext()).orElseThrow();
        } else {
            // TODO: select step based on condition
            nextStep = getStep(workflow, nextSteps.get(0).getNext()).orElseThrow();
        }

        workflow.nextStep(nextStep.getId());

        if (Step.TypeEnum.AUTO.equals(nextStep.getType())) {
            workflowsProducer.send("workflow.auto", workflow);

        } else if (Step.TypeEnum.HUMAN.equals(nextStep.getType())) {
            workflowsProducer.send("workflow.human", workflow);
        }
    }

}
