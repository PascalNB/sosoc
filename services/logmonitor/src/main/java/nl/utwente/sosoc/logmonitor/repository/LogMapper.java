package nl.utwente.sosoc.logmonitor.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.utwente.sosoc.logmonitor.model.Endpoint;
import nl.utwente.sosoc.logmonitor.model.LogEntry;

import java.util.Map;

public class LogMapper {

    public static LogEntry fromDb(DbLogEntry entry) {
        LogEntry logEntry = new LogEntry()
            .id(entry.getId())
            .event(entry.getEvent())
            .timestamp(entry.getTimestamp());
        Endpoint endpoint;
        try {
            endpoint = new ObjectMapper().readValue(entry.getEndpoint(), Endpoint.class);
        } catch (Exception e) {
            endpoint = null;
        }
        logEntry.setEndpoint(endpoint);
        Map<String, Object> data;
        try {
            data = new ObjectMapper().readValue(entry.getData(), Map.class);
        } catch (Exception e) {
            data = null;
        }
        logEntry.setData(data);
        return logEntry;
    }

    public static DbLogEntry toDb(LogEntry entry) {
        String data;
        try {
            data = new ObjectMapper().writeValueAsString(entry.getData());
        } catch (Exception e) {
            data = null;
        }
        String endpoint;
        try {
            endpoint = new ObjectMapper().writeValueAsString(entry.getEndpoint());
        } catch (Exception e) {
            endpoint = null;
        }
        return new DbLogEntry(entry.getTimestamp(), entry.getEvent(), endpoint, data);
    }

}
