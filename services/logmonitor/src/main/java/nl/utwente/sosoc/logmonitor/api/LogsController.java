package nl.utwente.sosoc.logmonitor.api;

import nl.utwente.sosoc.logmonitor.model.LogEntry;
import nl.utwente.sosoc.logmonitor.repository.LogMapper;
import nl.utwente.sosoc.logmonitor.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
public class LogsController implements LogsApi {

    @Autowired private LogRepository logRepository;

    @Override
    public ResponseEntity<LogEntry> getLog(UUID id) {
        return logRepository.findById(id)
            .map(LogMapper::fromDb)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<LogEntry>> getLogs() {
        List<LogEntry> logEntries = StreamSupport.stream(logRepository.findAll().spliterator(), false)
            .map(LogMapper::fromDb)
            .toList();
        return ResponseEntity.ok(logEntries);
    }

    @Override
    public ResponseEntity<Void> postLog(LogEntry logEntry) {
        logRepository.save(LogMapper.toDb(logEntry));
        return ResponseEntity.ok().build();
    }
}
