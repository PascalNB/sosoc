package nl.utwente.sosoc.logmonitor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.utwente.sosoc.logmonitor.model.Endpoint;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "logs")
@Getter
@Setter
@NoArgsConstructor
public class LogEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private OffsetDateTime timestamp;
    private String event;
    @JdbcTypeCode(SqlTypes.JSON)
    private Endpoint endpoint;
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> data;

}
