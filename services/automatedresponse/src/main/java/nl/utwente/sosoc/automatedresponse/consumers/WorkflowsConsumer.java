package nl.utwente.sosoc.automatedresponse.consumers;

import nl.utwente.sosoc.automatedresponse.model.Workflow;
import nl.utwente.sosoc.automatedresponse.service.AutomatedResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class WorkflowsConsumer {

    @Autowired AutomatedResponseService automatedResponseService;

    /**
     * Receives a workflow from the {@code workflow.auto} message queue and executes the next step.
     *
     * @param workflow the workflow instance
     */
    @JmsListener(destination = "workflow.auto")
    public void receiveMessage(Workflow workflow) {
        System.out.println("Received workflow: " + workflow.getId() + " " + workflow.getPlaybook().getName());
        automatedResponseService.execute(workflow);
    }

}
