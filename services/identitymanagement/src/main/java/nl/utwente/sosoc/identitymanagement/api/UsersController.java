package nl.utwente.sosoc.identitymanagement.api;

import nl.utwente.sosoc.identitymanagement.service.IdentityManagementService;
import nl.utwente.sosoc.identitymanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class UsersController implements UsersApi {

    @Autowired private IdentityManagementService identityManagementService;

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(identityManagementService.getUsers());
    }

    @Override
    public ResponseEntity<User> getUser(UUID id) {
        User user = identityManagementService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> postUser(User user) {
        identityManagementService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> putUser(UUID id, User user) {
        if (identityManagementService.updateUser(id, user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        if (identityManagementService.deleteUser(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
