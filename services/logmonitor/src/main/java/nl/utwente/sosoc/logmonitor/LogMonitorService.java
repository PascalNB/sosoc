package nl.utwente.sosoc.logmonitor;

import jakarta.annotation.PostConstruct;
import nl.utwente.sosoc.logmonitor.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LogMonitorService {

    @Autowired private ApplicationContext context;
    private static Map<UUID, Rule> rules = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        saveRule(new Rule()
            .id(UUID.randomUUID())
            .name("External email")
            .interval("0 */1 * * * *")
            // Aware of possible SQL injection, but out of scope. We wanted a similar functionality as KQL in Azure
            .query("SELECT endpoint, data->'email' as email FROM logs WHERE event='email-received' " +
                "AND data->'email'->>'source' NOT LIKE '%example.com' " +
                "AND timestamp >= NOW() - INTERVAL '1 minutes'")
            .threshold(1)
            .group(1)
            .threat(new Threat()
                .code("external email")
                .severity(Severity.MEDIUM)
            )
            .fields(List.of(
                new RuleFieldsInner()
                    .field("endpoint")
                    .type("object"),
                new RuleFieldsInner()
                    .field("email")
                    .type("object")
            ))
        );
    }


    /**
     * Get a rule by its id.
     * @param id The id of the rule.
     * @return The rule.
     */
    public Rule getRuleById(UUID id) {
        return rules.get(id);
    }

    /**
     * Get all rules.
     * @return A list of all rules.
     */
    public List<Rule> getRules() {
        return rules.values().stream().toList();
    }

    /**
     * Save a rule with a random id.
     * @param rule The rule to save.
     */
    public void saveRule(Rule rule) {
        UUID newId = UUID.randomUUID();
        rules.put(newId, rule.id(newId));
        context.getBean(RuleSchedulerService.class).scheduleRule(rule);
    }

    /**
     * Delete a rule by its id.
     * @param id of the rule to be deleted.
     */
    public void deleteRule(UUID id) {
        rules.remove(id);
        context.getBean(RuleSchedulerService.class).cancelTask(id);
    }
}
