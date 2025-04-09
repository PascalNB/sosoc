package nl.utwente.sosoc.logmonitor.repository;

import nl.utwente.sosoc.logmonitor.entity.LogEntryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LogRepository extends CrudRepository<LogEntryEntity, UUID> {
}
