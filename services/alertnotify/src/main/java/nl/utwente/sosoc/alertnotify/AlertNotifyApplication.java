package nl.utwente.sosoc.alertnotify;

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
    basePackages = {"nl.utwente.sosoc.alertnotify", "nl.utwente.sosoc.alertnotify.api" , "nl.utwente.sosoc.alertnotify.configuration"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class AlertNotifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlertNotifyApplication.class, args);
    }

    @Bean(name = "nl.utwente.sosoc.alertnotify.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}