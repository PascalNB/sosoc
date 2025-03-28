package nl.utwente.sosoc.logmonitor.repository;

import jakarta.persistence.*;
import nl.utwente.sosoc.logmonitor.model.Endpoint;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "logs")
public class DbLogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private OffsetDateTime timestamp;

    private String event;

    @JdbcTypeCode(SqlTypes.JSON)
    private Endpoint endpoint;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> data;

    public DbLogEntry() {
    }

    public DbLogEntry(OffsetDateTime timestamp, String event, Endpoint endpoint, Map<String, Object> data) {
        this.timestamp = timestamp;
        this.event = event;
        this.endpoint = endpoint;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public String getEvent() {
        return event;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public Map<String, Object> getData() {
        return data;
    }

}
