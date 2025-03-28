package nl.utwente.sosoc.logmonitor.repository;

import nl.utwente.sosoc.logmonitor.model.LogEntry;

public class LogMapper {

    public static LogEntry fromDb(DbLogEntry entry) {
        return new LogEntry()
            .id(entry.getId())
            .event(entry.getEvent())
            .timestamp(entry.getTimestamp())
            .endpoint(entry.getEndpoint())
            .data(entry.getData());
    }

    public static DbLogEntry toDb(LogEntry entry) {
        // ID will be generated created
        return new DbLogEntry(entry.getTimestamp(), entry.getEvent(), entry.getEndpoint(), entry.getData());
    }

}
