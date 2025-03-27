package nl.utwente.sosoc.threatintelligence.api;

import nl.utwente.sosoc.threatintelligence.ThreatIntelligenceService;
import nl.utwente.sosoc.threatintelligence.model.IOC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class IocsController implements IocsApi {

    @Autowired private ThreatIntelligenceService threatIntelligenceService;

    @Override
    public ResponseEntity<List<IOC>> getIOCs() {
        return ResponseEntity.ok(threatIntelligenceService.getIocs());
    }

    @Override
    public ResponseEntity<IOC> getIOC(UUID id) {
        IOC ioc = threatIntelligenceService.getIoc(id);
        if (ioc == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ioc);
    }

    @Override
    public ResponseEntity<Void> postIOC(IOC ioc) {
        threatIntelligenceService.createIoc(ioc);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> putIOC(UUID id, IOC ioc) {
        if (threatIntelligenceService.updateIoc(id, ioc)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteIOC(UUID id) {
        if (threatIntelligenceService.deleteIoc(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
