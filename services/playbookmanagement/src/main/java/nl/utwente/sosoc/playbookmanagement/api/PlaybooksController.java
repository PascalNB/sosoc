package nl.utwente.sosoc.playbookmanagement.api;

import nl.utwente.sosoc.playbookmanagement.PlaybookManagementService;
import nl.utwente.sosoc.playbookmanagement.model.Playbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class PlaybooksController implements PlaybooksApi {
    @Autowired private PlaybookManagementService playbookManagementService;

    @Override
    public ResponseEntity<Playbook> getPlaybook(UUID id) {
        if (playbookManagementService.getPlaybookById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playbookManagementService.getPlaybookById(id));
    }

    @Override
    public ResponseEntity<List<Playbook>> getPlaybooks() {
        if (playbookManagementService.getPlaybooks().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(playbookManagementService.getPlaybooks());
    }

    @Override
    public ResponseEntity<Void> postPlaybook(Playbook playbook) {
        playbookManagementService.savePlaybook(playbook);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> putPlaybook(UUID id, Playbook playbook) {
        if (playbookManagementService.getPlaybookById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        playbookManagementService.savePlaybook(playbook);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deletePlaybook(UUID id) {
        if (playbookManagementService.getPlaybookById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        playbookManagementService.deletePlaybook(id);
        return ResponseEntity.ok().build();
    }
}
