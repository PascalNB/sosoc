package nl.utwente.sosoc.userinterface.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.utwente.sosoc.userinterface.model.Rule;
import nl.utwente.sosoc.userinterface.model.User;
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

    private <T> List<T> retrieveAll(Class<T> type, String uri) throws JsonProcessingException {
        RestTemplate restTemplate = restTemplateBuilder.rootUri("http://localhost:" + port).build();
        String json = restTemplate.getForObject(uri, String.class);
        return objectMapper.readerForListOf(type).readValue(json);
    }

}
