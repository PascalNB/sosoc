package nl.utwente.sosoc.logmonitor.api;

import nl.utwente.sosoc.logmonitor.LogMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RulesController {
    @Autowired LogMonitorService logMonitorService;


}
