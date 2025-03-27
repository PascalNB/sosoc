package nl.utwente.sosoc.threatintelligence.api;

import nl.utwente.sosoc.threatintelligence.ThreatIntelligenceService;
import nl.utwente.sosoc.threatintelligence.model.Alarm;
import nl.utwente.sosoc.threatintelligence.model.IOC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlarmsController implements AlarmsApi {

    @Autowired ThreatIntelligenceService threatIntelligenceService;

    @Override
    public ResponseEntity<IOC> postAlarm(Alarm alarm) {
        IOC ioc = threatIntelligenceService.detectIoc(alarm);
        if (ioc != null) {
            return ResponseEntity.ok(ioc);
        }
        return ResponseEntity.notFound().build();
    }

}
