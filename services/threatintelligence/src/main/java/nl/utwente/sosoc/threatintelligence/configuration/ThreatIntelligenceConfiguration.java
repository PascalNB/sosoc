package nl.utwente.sosoc.threatintelligence.configuration;

import nl.utwente.sosoc.threatintelligence.util.EntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreatIntelligenceConfiguration {

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper();
    }

}
