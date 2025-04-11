package nl.utwente.sosoc.identitymanagement.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import nl.utwente.sosoc.identitymanagement.model.Endpoint;

import java.io.IOException;
import java.util.List;

@Converter(autoApply = true)
public class EndpointListConverter implements AttributeConverter<List<Endpoint>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Endpoint> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting Endpoint to JSON", e);
        }
    }

    @Override
    public List<Endpoint> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readerForListOf(Endpoint.class).readValue(dbData);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to Endpoint", e);
        }
    }

}
