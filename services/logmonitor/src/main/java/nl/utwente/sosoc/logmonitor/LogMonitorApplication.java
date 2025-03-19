package nl.utwente.sosoc.logmonitor;

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
    basePackages = {"nl.utwente.sosoc.logmonitor", "nl.utwente.sosoc.logmonitor.api"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class LogMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogMonitorApplication.class, args);
    }

    @Bean(name = "nl.utwente.sosoc.logmonitor.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}