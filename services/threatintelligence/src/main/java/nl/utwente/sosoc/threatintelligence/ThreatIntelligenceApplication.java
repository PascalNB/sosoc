package nl.utwente.sosoc.threatintelligence;

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
    basePackages = {"nl.utwente.sosoc.threatintelligence", "nl.utwente.sosoc.threatintelligence.api"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class ThreatIntelligenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreatIntelligenceApplication.class, args);
    }

    @Bean(name = "nl.utwente.sosoc.threatintelligence.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}