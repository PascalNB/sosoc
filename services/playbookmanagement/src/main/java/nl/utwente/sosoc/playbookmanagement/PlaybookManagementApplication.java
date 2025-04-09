package nl.utwente.sosoc.playbookmanagement;

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
    basePackages = {"nl.utwente.sosoc.playbookmanagement", "nl.utwente.sosoc.playbookmanagement.api"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@EntityScan("nl.utwente.sosoc.playbookmanagement.entity")
@EnableJpaRepositories("nl.utwente.sosoc.playbookmanagement.repository")
public class PlaybookManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaybookManagementApplication.class, args);
    }

    @Bean(name = "nl.utwente.sosoc.playbookmanagement.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}