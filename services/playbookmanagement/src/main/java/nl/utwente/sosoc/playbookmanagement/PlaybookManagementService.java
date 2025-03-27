package nl.utwente.sosoc.playbookmanagement;

import nl.utwente.sosoc.playbookmanagement.model.Playbook;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaybookManagementService {
    private static Map<UUID, Playbook> playbooks = new HashMap<>();

    /**
     * Get a playbook by its ID.
     * @param id the ID of the playbook
     * @return the playbook with the given ID
     */
    public Playbook getPlaybookById(UUID id) {
        return playbooks.get(id);
    }

    /**
     * Get all playbooks.
     * @return a list of all playbooks.
     */
    public List<Playbook> getPlaybooks() {
        return playbooks.values().stream().toList();
    }

    /**
     * Save a new playbook with a randomID.
     * @param playbook the playbook to save.
     */
    public void savePlaybook(Playbook playbook) {
        UUID newId = UUID.randomUUID();
        playbook.setId(newId);
        playbooks.put(newId, playbook);
    }

    /**
     * Remove a given playbook.
     * @param id of the playbook to remove.
     */
    public void deletePlaybook(UUID id) {
        playbooks.remove(id);
    }
}
