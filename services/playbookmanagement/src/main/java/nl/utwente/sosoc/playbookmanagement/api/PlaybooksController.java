package nl.utwente.sosoc.playbookmanagement.api;

import nl.utwente.sosoc.playbookmanagement.entity.PlaybookEntity;
import nl.utwente.sosoc.playbookmanagement.repository.PlaybookRepository;
import nl.utwente.sosoc.playbookmanagement.service.PlaybookManagementService;
import nl.utwente.sosoc.playbookmanagement.model.Playbook;
import nl.utwente.sosoc.playbookmanagement.util.EntityMapper;
import org.modelmapper.spi.DestinationSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class PlaybooksController implements PlaybooksApi {

    @Autowired private PlaybookRepository playbookRepository;
    @Autowired private EntityMapper entityMapper;

    @Override
    public ResponseEntity<Playbook> getPlaybook(UUID id) {
        return playbookRepository.findById(id)
            .map(entityMapper.to(Playbook.class))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Playbook>> getPlaybooks() {
        List<Playbook> playbooks = StreamSupport.stream(playbookRepository.findAll().spliterator(), false)
            .map(entityMapper.to(Playbook.class))
            .toList();
        return ResponseEntity.ok(playbooks);
    }

    @Override
    public ResponseEntity<Void> postPlaybook(Playbook playbook) {
        playbookRepository.save(entityMapper.to(
            PlaybookEntity.class,
            (DestinationSetter<PlaybookEntity, UUID>) PlaybookEntity::setId
        ).apply(playbook));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> putPlaybook(UUID id, Playbook playbook) {
        playbookRepository.save(entityMapper.to(PlaybookEntity.class).apply(playbook.id(id)));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deletePlaybook(UUID id) {
        if (playbookRepository.existsById(id)) {
            playbookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
