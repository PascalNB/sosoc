package nl.utwente.sosoc.identitymanagement.api;

import nl.utwente.sosoc.identitymanagement.IdentityManagementService;
import nl.utwente.sosoc.identitymanagement.model.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionsController implements ActionsApi {

    @Autowired private IdentityManagementService identityManagementService;

    @Override
    public ResponseEntity<Void> postAction(Action action) {
        System.out.println("Executing action " + action.getAction().getCode() +
            " on user " + action.getUser().getName());
        return ResponseEntity.ok().build();
    }

}
