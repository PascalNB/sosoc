package nl.utwente.sosoc.logmonitor.service;

import jakarta.annotation.PostConstruct;
import nl.utwente.sosoc.logmonitor.entity.RuleEntity;
import nl.utwente.sosoc.logmonitor.model.*;
import nl.utwente.sosoc.logmonitor.repository.RuleRepository;
import nl.utwente.sosoc.logmonitor.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LogMonitorService {

    @Autowired private RuleRepository ruleRepository;
    @Autowired private EntityMapper entityMapper;
    @Autowired private RuleSchedulerService ruleSchedulerService;

    @PostConstruct
    public void init() {
        Rule rule = new Rule()
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
            ));
        ruleRepository.save(entityMapper.to(RuleEntity.class).apply(rule));
        ruleSchedulerService.scheduleRule(rule);
    }

}
