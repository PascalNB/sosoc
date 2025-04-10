package nl.utwente.sosoc.alertnotify.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.utwente.sosoc.alertnotify.util.RFC3339DateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlertNotifyConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(RFC3339DateFormat.getInstance());
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

}
