package nl.utwente.sosoc.logmonitor.repository;

import nl.utwente.sosoc.logmonitor.entity.RuleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RuleRepository extends CrudRepository<RuleEntity, UUID> {
}
