package nl.utwente.sosoc.logmonitor.api;

import nl.utwente.sosoc.logmonitor.entity.RuleEntity;
import nl.utwente.sosoc.logmonitor.repository.RuleRepository;
import nl.utwente.sosoc.logmonitor.service.LogMonitorService;
import nl.utwente.sosoc.logmonitor.model.Rule;
import nl.utwente.sosoc.logmonitor.service.RuleSchedulerService;
import nl.utwente.sosoc.logmonitor.util.EntityMapper;
import org.modelmapper.spi.DestinationSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Controller
public class RulesController implements RulesApi {

    @Autowired private RuleRepository ruleRepository;
    @Autowired private RuleSchedulerService ruleSchedulerService;
    @Autowired private EntityMapper entityMapper;

    @Override
    public ResponseEntity<Rule> getRule(UUID id) {
        return ruleRepository.findById(id)
            .map(entityMapper.to(Rule.class))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Rule>> getRules() {
        List<Rule> rules = StreamSupport.stream(ruleRepository.findAll().spliterator(), false)
            .map(entityMapper.to(Rule.class))
            .toList();
        return ResponseEntity.ok(rules);
    }

    @Override
    public ResponseEntity<Void> postRule(Rule rule) {
        RuleEntity ruleEntity = ruleRepository.save(entityMapper.to(
            RuleEntity.class,
            (DestinationSetter<RuleEntity, UUID>) RuleEntity::setId // skip ID
        ).apply(rule));
        rule.setId(ruleEntity.getId());
        ruleSchedulerService.scheduleRule(rule);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> putRule(UUID id, Rule rule) {
        RuleEntity ruleEntity = ruleRepository.save(entityMapper.to(RuleEntity.class).apply(rule.id(id)));
        rule.setId(ruleEntity.getId());
        ruleSchedulerService.scheduleRule(rule);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteRule(UUID id) {
        if (ruleRepository.existsById(id)) {
            ruleRepository.deleteById(id);
            ruleSchedulerService.cancelTask(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
