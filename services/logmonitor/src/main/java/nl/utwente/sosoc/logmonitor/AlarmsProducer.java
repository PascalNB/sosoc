package nl.utwente.sosoc.logmonitor;

import nl.utwente.sosoc.logmonitor.model.Alarm;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlarmsProducer {

    private final JmsTemplate jmsTemplate;

    public AlarmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String destination, Alarm alarm) {
        jmsTemplate.convertAndSend(destination, alarm, message -> {
            message.setJMSType("alarm");
            return message;
        });
        System.out.println("Sent alarm: " + alarm.getId() + " " + alarm.getRule().getName());
    }

}
