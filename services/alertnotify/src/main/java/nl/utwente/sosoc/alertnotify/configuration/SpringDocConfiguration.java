package nl.utwente.sosoc.alertnotify.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean(name = "nl.utwente.sosoc.alertnotify.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Alerting &amp; Notification Service")
                                .description("Generates alerts for detected threats and notifies SOC analysts or IT admins via email, SMS, etc.")
                                .version("1.0.0")
                )
        ;
    }
}