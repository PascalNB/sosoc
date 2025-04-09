package nl.utwente.sosoc.playbookmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.utwente.sosoc.playbookmanagement.model.Step;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "playbooks")
@Getter
@Setter
@NoArgsConstructor
public class PlaybookEntity {

    @Id
    private UUID id;
    private String name;
    private String description;
    private String trigger;
    private UUID firstStep;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Step> steps;

}
