package nl.utwente.sosoc.alertnotify;

import nl.utwente.sosoc.alertnotify.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class WorkflowsConsumer {

    @Autowired private AlertNotifyService alertNotifyService;

    /**
     * Receives a workflow from the {@code workflow.human} message queue and executes the next step.
     *
     * @param workflow the workflow instance
     */
    @JmsListener(destination = "workflow.human")
    public void receiveMessage(Workflow workflow) {
        System.out.println("Received workflow: " + workflow.getId() + " " + workflow.getPlaybook().getName());
        alertNotifyService.execute(workflow);
    }

}
