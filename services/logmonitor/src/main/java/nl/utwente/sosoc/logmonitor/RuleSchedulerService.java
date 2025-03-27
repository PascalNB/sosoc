package nl.utwente.sosoc.logmonitor;

import nl.utwente.sosoc.logmonitor.model.Rule;
import nl.utwente.sosoc.logmonitor.model.RuleFieldsInner;
import nl.utwente.sosoc.logmonitor.model.Severity;
import nl.utwente.sosoc.logmonitor.model.Threat;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

public class RuleSchedulerService {

    private final ThreadPoolTaskScheduler scheduler;
    private final LogMonitorService logMonitorService;
    private final RuleExecutor ruleExecutor;
    private final Map<String, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

    public RuleSchedulerService(ThreadPoolTaskScheduler scheduler, LogMonitorService logMonitorService) {
        this.scheduler = scheduler;
        this.logMonitorService = logMonitorService;
        this.ruleExecutor = new RuleExecutor();
    }

    public void init() {
        logMonitorService.saveRule(new Rule()
            .id(UUID.randomUUID())
            .name("External email")
            .interval("0 */1 * * * *")
            .query("SELECT * FROM logs WHERE type='email-received' AND data->'email'->>'source' NOT LIKE %example.com" +
                " AND timestamp >= NOW() - INTERVAL '1 minutes';")
            .threshold(BigDecimal.ONE)
            .threat(new Threat()
                .code("external email")
                .severity(Severity.MEDIUM)
            )
            .fields(List.of(
                new RuleFieldsInner()
                    .field("email")
                    .type("object")
            ))
        );
    }

    public void scheduleRule(Rule rule) {
        scheduleTask(rule.getId().toString(), () -> ruleExecutor.execute(rule), rule.getInterval());
    }

    public void scheduleTask(String taskId, Runnable task, String cronExpression) {
        cancelTask(taskId); // Ensure no duplicate tasks
        ScheduledFuture<?> future = scheduler.schedule(task, new CronTrigger(cronExpression));
        tasks.put(taskId, future);
        System.out.println("Scheduled task: " + taskId + " with interval " + cronExpression);
    }

    public void cancelTask(String taskId) {
        ScheduledFuture<?> future = tasks.remove(taskId);
        if (future != null) {
            future.cancel(false);
            System.out.println("Cancelled task: " + taskId);
        }
    }

}
