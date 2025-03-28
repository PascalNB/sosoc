package nl.utwente.sosoc.logmonitor.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import nl.utwente.sosoc.logmonitor.model.Endpoint;

import java.io.IOException;

@Converter(autoApply = true)
public class EndpointConverter implements AttributeConverter<Endpoint, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Endpoint attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting Endpoint to JSON", e);
        }
    }

    @Override
    public Endpoint convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Endpoint.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to Endpoint", e);
        }
    }

}
