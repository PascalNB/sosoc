package nl.utwente.sosoc.userinterface.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.utwente.sosoc.userinterface.util.RFC3339DateFormat;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInterfaceConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(RFC3339DateFormat.getInstance());
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        // @RestTemplateAutoConfiguration does not work with webflux
        return new RestTemplateBuilder();
    }

}
