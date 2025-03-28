package nl.utwente.sosoc.logmonitor.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LogRepository extends CrudRepository<DbLogEntry, UUID> {

}
