package nl.utwente.sosoc.identitymanagement.configuration;

import nl.utwente.sosoc.identitymanagement.util.EntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityManagementConfiguration {

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper();
    }

}
