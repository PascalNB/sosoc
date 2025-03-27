package nl.utwente.sosoc.playbookmanagement.api;

import nl.utwente.sosoc.playbookmanagement.model.Playbook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlaybookController implements PlaybooksApi {

    @Override
    public ResponseEntity<List<Playbook>> getPlaybooks() {
        return PlaybooksApi.super.getPlaybooks();
    }

}
