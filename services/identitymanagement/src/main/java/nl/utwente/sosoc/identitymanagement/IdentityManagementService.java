package nl.utwente.sosoc.identitymanagement;

import jakarta.annotation.PostConstruct;
import nl.utwente.sosoc.identitymanagement.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IdentityManagementService {

    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        addUser(new User()
            .name("Pascal")
            .email("p.n.bakker@student.utwente.nl")
            .role("analyst")
        );
        addUser(new User()
            .name("Matei")
            .email("m.obrocea@student.utwente.nl")
            .role("analyst")
        );
    }

    /**
     * @return a list of all users
     */
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    /**
     * Get a user by ID
     *
     * @param id the ID
     * @return the user or null
     */
    public User getUser(UUID id) {
        return users.get(id);
    }

    /**
     * Add a new user with a random UUID.
     *
     * @param user the user
     */
    public void addUser(User user) {
        UUID id = UUID.randomUUID();
        users.put(id, user.id(id));
    }

    /**
     * Update an existing user by ID.
     *
     * @param id   the ID
     * @param user the modified user
     * @return whether the user was found for the given ID and updated
     */
    public boolean updateUser(UUID id, User user) {
        if (!users.containsKey(id)) {
            return false;
        }
        // TODO: properly update user
        users.put(id, user);
        return true;
    }

    /**
     * Delete an existing user by ID.
     *
     * @param id the ID
     * @return whether the user was found for the given ID and deleted
     */
    public boolean deleteUser(UUID id) {
        return users.remove(id) != null;
    }

}
