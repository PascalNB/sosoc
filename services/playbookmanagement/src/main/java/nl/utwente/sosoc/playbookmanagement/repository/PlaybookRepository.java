package nl.utwente.sosoc.playbookmanagement.repository;

import nl.utwente.sosoc.playbookmanagement.entity.PlaybookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlaybookRepository extends CrudRepository<PlaybookEntity, UUID> {

}
