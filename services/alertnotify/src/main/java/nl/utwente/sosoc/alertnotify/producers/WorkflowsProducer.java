package nl.utwente.sosoc.alertnotify.producers;

import nl.utwente.sosoc.alertnotify.model.Workflow;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class WorkflowsProducer {

    private final JmsTemplate jmsTemplate;

    public WorkflowsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String destination, Workflow workflow) {
        jmsTemplate.convertAndSend(destination, workflow, message -> {
            message.setJMSType("workflow");
            return message;
        });
        System.out.println("Sent workflow: " + workflow.getId() + " to destination: " + destination);
    }

}
