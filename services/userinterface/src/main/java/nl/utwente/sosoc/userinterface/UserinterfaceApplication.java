package nl.utwente.sosoc.userinterface;

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
    basePackages = {"nl.utwente.sosoc.userinterface", "nl.utwente.sosoc.userinterface.api"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class UserinterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserinterfaceApplication.class, args);
    }

    @Bean(name = "nl.utwente.sosoc.userinterface.OpenApiGeneratorApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}
