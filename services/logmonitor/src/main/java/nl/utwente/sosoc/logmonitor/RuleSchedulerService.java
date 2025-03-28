package nl.utwente.sosoc.logmonitor;

import nl.utwente.sosoc.logmonitor.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class RuleSchedulerService {

    @Autowired private ThreadPoolTaskScheduler scheduler;
    @Autowired private RuleExecutor ruleExecutor;
    private final Map<UUID, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

    public void scheduleRule(Rule rule) {
        scheduleTask(rule.getId(), () -> ruleExecutor.execute(rule), rule.getInterval());
    }

    public void scheduleTask(UUID taskId, Runnable task, String cronExpression) {
        cancelTask(taskId); // Ensure no duplicate tasks
        ScheduledFuture<?> future = scheduler.schedule(task, new CronTrigger(cronExpression));
        tasks.put(taskId, future);
        System.out.println("Scheduled task: " + taskId + " with interval " + cronExpression);
    }

    public void cancelTask(UUID taskId) {
        ScheduledFuture<?> future = tasks.remove(taskId);
        if (future != null) {
            future.cancel(false);
            System.out.println("Cancelled task: " + taskId);
        }
    }

}
