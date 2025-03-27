package nl.utwente.sosoc.playbookmanagement;

import nl.utwente.sosoc.playbookmanagement.model.Alarm;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AlarmsConsumer {


    /**
     * Receives an alarm from the {@code alarms} message queue and processes it.
     *
     * @param alarm the alarm instance
     */
    @JmsListener(destination = "alarms")
    public void receiveMessage(Alarm alarm) {
        System.out.println("Received alarm: " + alarm.getId() + " " + alarm.getRule().getName());
    }

}
