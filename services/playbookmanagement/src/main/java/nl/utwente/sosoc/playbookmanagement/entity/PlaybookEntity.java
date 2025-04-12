package nl.utwente.sosoc.playbookmanagement.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    private String trigger;
    private String firstStep;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Step> steps;

}
