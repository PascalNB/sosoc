package nl.utwente.sosoc.automatedresponse;

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
    basePackages = {"nl.utwente.sosoc.automatedresponse", "nl.utwente.sosoc.automatedresponse.api"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class AutomatedResponseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomatedResponseApplication.class, args);
    }

    @Bean(name = "nl.utwente.sosoc.automatedresponse.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}