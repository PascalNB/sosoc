package nl.utwente.sosoc.automatedresponse.configuration;

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

    @Bean(name = "nl.utwente.sosoc.automatedresponse.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Automated Response Service")
                                .description("Takes automated actions when threats are detected. For example, disabling accounts, blocking network sources, or quarantining endpoint devices.")
                                .version("1.0.0")
                )
        ;
    }
}