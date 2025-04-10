package nl.utwente.sosoc.identitymanagement.api;

import nl.utwente.sosoc.identitymanagement.entity.UserEntity;
import nl.utwente.sosoc.identitymanagement.repository.UserRepository;
import nl.utwente.sosoc.identitymanagement.service.IdentityManagementService;
import nl.utwente.sosoc.identitymanagement.model.User;
import nl.utwente.sosoc.identitymanagement.util.EntityMapper;
import org.modelmapper.spi.DestinationSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
public class UsersController implements UsersApi {

    @Autowired private UserRepository userRepository;
    @Autowired private EntityMapper entityMapper;

    @Override
    public ResponseEntity<List<User>> getUsers(String role) {
        List<User> users;
        if (role == null) {
            users = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(entityMapper.to(User.class))
                .toList();
        } else {
            users = userRepository.findAllByRole(role).stream()
                .map(entityMapper.to(User.class))
                .toList();
        }
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUser(UUID id) {
        return userRepository.findById(id)
            .map(entityMapper.to(User.class))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> postUser(User user) {
        userRepository.save(entityMapper.to(
            UserEntity.class,
            (DestinationSetter<UserEntity, UUID>) UserEntity::setId // skip ID
        ).apply(user));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> putUser(UUID id, User user) {
        userRepository.save(entityMapper.to(UserEntity.class).apply(user.id(id)));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
