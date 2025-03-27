package nl.utwente.sosoc.logmonitor;

import nl.utwente.sosoc.logmonitor.model.LogEntry;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogMonitorService {
    private static Map<UUID, LogEntry> logEntries = new HashMap<>();
    private static long idCounter = 1;

    public LogEntry getLog(UUID id) {
        return logEntries.get(id);
    }

    public List<LogEntry> getLogs() {
        return logEntries.values().stream().toList();
    }

    public void saveLogEntry(LogEntry logEntry) {
        UUID newId = new UUID(0L, idCounter++);
        logEntry.setId(newId);
        logEntries.put(newId, logEntry);
    }
}
