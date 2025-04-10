package nl.utwente.sosoc.identitymanagement.repository;

import nl.utwente.sosoc.identitymanagement.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    List<UserEntity> findAllByRole(String role);

}
