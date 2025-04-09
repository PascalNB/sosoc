package nl.utwente.sosoc.playbookmanagement.configuration;

import nl.utwente.sosoc.playbookmanagement.util.EntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaybookManagementConfiguration {

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper();
    }

}
