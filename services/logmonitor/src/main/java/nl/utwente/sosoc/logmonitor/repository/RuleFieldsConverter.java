package nl.utwente.sosoc.logmonitor.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import nl.utwente.sosoc.logmonitor.model.RuleFieldsInner;

import java.io.IOException;
import java.util.List;

@Converter(autoApply = true)
public class RuleFieldsConverter implements AttributeConverter<List<RuleFieldsInner>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<RuleFieldsInner> ruleFieldsInners) {
        try {
            return objectMapper.writeValueAsString(ruleFieldsInners);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting map to JSON", e);
        }
    }

    @Override
    public List<RuleFieldsInner> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readerForListOf(RuleFieldsInner.class).readValue(s);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to map", e);
        }
    }

}
