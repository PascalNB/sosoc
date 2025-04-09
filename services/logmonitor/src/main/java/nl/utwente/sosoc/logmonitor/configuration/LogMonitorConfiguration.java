package nl.utwente.sosoc.logmonitor.configuration;

import nl.utwente.sosoc.logmonitor.util.EntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogMonitorConfiguration {

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper();
    }

}
