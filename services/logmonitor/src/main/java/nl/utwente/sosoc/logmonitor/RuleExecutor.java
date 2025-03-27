package nl.utwente.sosoc.logmonitor;

import nl.utwente.sosoc.logmonitor.model.Rule;
import org.springframework.stereotype.Component;

@Component
public class RuleExecutor {

    public void execute(Rule rule) {
        System.out.println("Executing rule: " + rule.getName());
        // TODO: execute rule query on log database
        // TODO: extract fields
        // TODO: create alarm
        // TODO: send request to threat intelligence for IOC
        // TODO: add alarm to message queue
    }

}
