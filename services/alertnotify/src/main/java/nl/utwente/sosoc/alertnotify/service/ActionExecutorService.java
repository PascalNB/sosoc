package nl.utwente.sosoc.alertnotify.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.utwente.sosoc.alertnotify.model.Alarm;
import nl.utwente.sosoc.alertnotify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
public class ActionExecutorService {

    @Autowired private RestTemplateBuilder restTemplateBuilder;
    @Autowired private EmailSenderService emailSenderService;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TemplateEngine templateEngine;

    public void notify(String[] action, Alarm alarm) {
        String role = action[1];
        List<User> users;
        try {
            RestTemplate restTemplate = restTemplateBuilder.build();
            String url = "http://identitymanagement:8082/users?role={role}";
            String jsonResponse = restTemplate.getForEntity(url, String.class, role).getBody();
            users = objectMapper.readerForListOf(User.class).readValue(jsonResponse);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        if (users.isEmpty()) {
            return;
        }
        List<String> emails =  users.stream().map(User::getEmail).toList();
        for (String email : emails) {
            System.out.println("Notify " + email + " for " + alarm.getThreat().getCode());
        }
        String[] recipients = emails.toArray(new String[0]);
        String subject = "ALERT: " + alarm.getThreat().getCode() + " (" + alarm.getRule().getName() + ")";

        Context context = new Context();
        context.setVariable("alarm", alarm);

        String data;
        try {
            data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(alarm.getData());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            data = "";
        }
        context.setVariable("data", data);
        String body = templateEngine.process("soc-alert-email", context);
        try {
            emailSenderService.sendEmail(recipients, subject, body);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
