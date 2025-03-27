package nl.utwente.sosoc.logmonitor;

import jakarta.annotation.PostConstruct;
import nl.utwente.sosoc.logmonitor.model.LogEntry;
import nl.utwente.sosoc.logmonitor.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LogMonitorService {

    private final RuleSchedulerService ruleSchedulerService;
    private static Map<UUID, LogEntry> logEntries = new ConcurrentHashMap<>();
    private static Map<UUID, Rule> rules = new ConcurrentHashMap<>();

    public LogMonitorService(ThreadPoolTaskScheduler scheduler) {
        this.ruleSchedulerService = new RuleSchedulerService(scheduler, this);
    }

    @PostConstruct
    public void init() {
        this.ruleSchedulerService.init();
    }

    /**
     * Get a log entry by its id.
     * @param id The id of the log entry
     * @return The log entry.
     */
    public LogEntry getLogById(UUID id) {
        return logEntries.get(id);
    }

    /**
     * Get all log entries.
     * @return A list of all log entries.
     */
    public List<LogEntry> getLogs() {
        return logEntries.values().stream().toList();
    }

    /**
     * Save a log entry with a random id.
     * @param logEntry The log entry to save.
     */
    public void saveLogEntry(LogEntry logEntry) {
        UUID newId = UUID.randomUUID();
        logEntry.setId(newId);
        logEntries.put(newId, logEntry);
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
        ruleSchedulerService.scheduleRule(rule);
    }

    /**
     * Delete a rule by its id.
     * @param id of the rule to be deleted.
     */
    public void deleteRule(UUID id) {
        rules.remove(id);
        ruleSchedulerService.cancelTask(id.toString());
    }
}
