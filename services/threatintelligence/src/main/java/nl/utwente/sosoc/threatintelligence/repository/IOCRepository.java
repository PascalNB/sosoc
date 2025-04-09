package nl.utwente.sosoc.threatintelligence.repository;

import nl.utwente.sosoc.threatintelligence.entity.IOCEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IOCRepository extends CrudRepository<IOCEntity, UUID> {
}
