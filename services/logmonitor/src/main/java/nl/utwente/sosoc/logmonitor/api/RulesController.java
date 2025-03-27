package nl.utwente.sosoc.logmonitor.api;

import nl.utwente.sosoc.logmonitor.LogMonitorService;
import nl.utwente.sosoc.logmonitor.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Controller
public class RulesController implements RulesApi {
    @Autowired private LogMonitorService logMonitorService;

    @Override
    public ResponseEntity<Rule> getRule(UUID id) {
        if (logMonitorService.getRule(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(logMonitorService.getRule(id));
    }

    @Override
    public ResponseEntity<List<Rule>> getRules() {
        if (logMonitorService.getRules().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(logMonitorService.getRules());
    }


    @Override
    public ResponseEntity<Void> postRule(Rule rule) {
        logMonitorService.saveRule(rule);
        return ResponseEntity.ok().build();
    }

    /**
     * Update a rule.
     * ToDO: Merge the rule into the existing rule.
     * @param id Alarm ID (required)
     * @param rule  (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<Void> putRule(UUID id, Rule rule) {
        logMonitorService.saveRule(rule);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteRule(UUID id) {
        logMonitorService.deleteRule(id);
        return ResponseEntity.ok().build();
    }
}
