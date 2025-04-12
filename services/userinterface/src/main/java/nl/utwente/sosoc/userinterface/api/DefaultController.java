package nl.utwente.sosoc.userinterface.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.utwente.sosoc.userinterface.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class DefaultController {

    @Autowired private ObjectMapper objectMapper;
    @Autowired private RestTemplateBuilder restTemplateBuilder;
    @Value("${server.port}") private String port;

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/",
        produces = {"text/html"}
    )
    public String getDefaultPage(Model model) {
        return "index";
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users",
        produces = {"text/html"}
    )
    public String getUsersPage(Model model) throws JsonProcessingException {
        List<User> users = retrieveAll(User.class, "/api/users");
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/rules",
        produces = {"text/html"}
    )
    public String getRulesPage(Model model) throws JsonProcessingException {
        List<Rule> rules = retrieveAll(Rule.class, "/api/rules");
        model.addAttribute("rules", rules);
        return "rules";
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/iocs",
        produces = {"text/html"}
    )
    public String getIocsPage(Model model) throws JsonProcessingException {
        List<IOC> iocs = retrieveAll(IOC.class, "/api/iocs");
        model.addAttribute("iocs", iocs);
        return "iocs";
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/playbooks",
        produces = {"text/html"}
    )
    public String getPlaybooksPage(Model model) throws JsonProcessingException {
        List<Playbook> playbooks = retrieveAll(Playbook.class, "/api/playbooks");
        model.addAttribute("playbooks", playbooks);
        return "playbooks";
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/logs",
        produces = {"text/html"}
    )
    public String getLogsPage(Model model) throws JsonProcessingException {
        List<LogEntry> logs = retrieveAll(LogEntry.class, "/api/logs?limit=100");
        List<PrettyLogEntry> prettyLogs = logs.stream()
            .map(logEntry -> {
                String prettyData;
                try {
                    prettyData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(logEntry.getData());
                } catch (JsonProcessingException e) {
                    prettyData = "{}";
                }
                return new PrettyLogEntry(
                    logEntry.getTimestamp(),
                    logEntry.getEvent(),
                    logEntry.getEndpoint(),
                    prettyData
                );
            })
            .toList();
        model.addAttribute("logs", prettyLogs);
        return "logs";
    }

    private <T> List<T> retrieveAll(Class<T> type, String uri) throws JsonProcessingException {
        RestTemplate restTemplate = restTemplateBuilder.rootUri("http://gateway:" + port).build();
        String json = restTemplate.getForObject(uri, String.class);
        return objectMapper.readerForListOf(type).readValue(json);
    }

}
