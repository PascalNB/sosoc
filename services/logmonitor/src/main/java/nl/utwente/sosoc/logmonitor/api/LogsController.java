package nl.utwente.sosoc.logmonitor.api;

import nl.utwente.sosoc.logmonitor.LogMonitorService;
import nl.utwente.sosoc.logmonitor.model.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class LogsController implements LogsApi {
    @Autowired private LogMonitorService logMonitorService;

    @Override
    public ResponseEntity<LogEntry> getLog(UUID id) {
        LogEntry entry = logMonitorService.getLog(id);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(logMonitorService.getLog(id));
    }

    @Override
    public ResponseEntity<List<LogEntry>> getLogs() {
        if (logMonitorService.getLogs().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(logMonitorService.getLogs());
    }
    // log entry received, create id and save
    @Override
    public ResponseEntity<Void> postLog(LogEntry logEntry) {
        logMonitorService.saveLogEntry(logEntry);
        return ResponseEntity.ok().build();
    }
}
