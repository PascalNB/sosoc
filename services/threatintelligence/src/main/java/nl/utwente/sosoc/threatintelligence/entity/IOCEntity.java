package nl.utwente.sosoc.threatintelligence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.utwente.sosoc.threatintelligence.model.Threat;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "iocs")
@Getter
@Setter
@NoArgsConstructor
public class IOCEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JdbcTypeCode(SqlTypes.JSON)
    private Threat threat;
    private String match;

}
