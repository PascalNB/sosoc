package nl.utwente.sosoc.identitymanagement;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@ComponentScan(
    basePackages = {"nl.utwente.sosoc.identitymanagement", "nl.utwente.sosoc.identitymanagement.api" , "nl.utwente.sosoc.identitymanagement.configuration"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class IdentityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityManagementApplication.class, args);
    }

    @Bean(name = "nl.utwente.sosoc.identitymanagement.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}