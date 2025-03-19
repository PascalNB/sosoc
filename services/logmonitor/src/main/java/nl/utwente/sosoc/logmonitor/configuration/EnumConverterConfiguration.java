package nl.utwente.sosoc.logmonitor.configuration;

import nl.utwente.sosoc.logmonitor.model.Severity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class EnumConverterConfiguration {

    @Bean(name = "nl.utwente.sosoc.logmonitor.configuration.EnumConverterConfiguration.severityConverter")
    Converter<String, Severity> severityConverter() {
        return new Converter<String, Severity>() {
            @Override
            public Severity convert(String source) {
                return Severity.fromValue(source);
            }
        };
    }

}
