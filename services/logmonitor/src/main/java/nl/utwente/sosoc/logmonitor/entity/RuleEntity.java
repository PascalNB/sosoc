package nl.utwente.sosoc.logmonitor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.utwente.sosoc.logmonitor.model.RuleFieldsInner;
import nl.utwente.sosoc.logmonitor.model.Threat;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "rules")
@Getter
@Setter
@NoArgsConstructor
public class RuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String interval;
    private String query;
    private Integer threshold;
    @Column(name = "group_count")
    private Integer group;
    @JdbcTypeCode(SqlTypes.JSON)
    private Threat threat;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<RuleFieldsInner> fields;

}
