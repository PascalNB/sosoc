package nl.utwente.sosoc.logmonitor.repository;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "logs")
public class DbLogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private OffsetDateTime timestamp;

    private String event;

    private String endpoint;

    private String data;

    public DbLogEntry() {
    }

    public DbLogEntry(OffsetDateTime timestamp, String event, String endpoint, String data) {
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

    public String getEndpoint() {
        return endpoint;
    }

    public String getData() {
        return data;
    }

}
