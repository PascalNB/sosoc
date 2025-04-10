package nl.utwente.sosoc.identitymanagement;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@ComponentScan(
    basePackages = {"nl.utwente.sosoc.identitymanagement", "nl.utwente.sosoc.identitymanagement.api"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@EntityScan("nl.utwente.sosoc.identitymanagement.entity")
@EnableJpaRepositories("nl.utwente.sosoc.identitymanagement.repository")
public class IdentityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityManagementApplication.class, args);
    }

    @Bean(name = "nl.utwente.sosoc.identitymanagement.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}