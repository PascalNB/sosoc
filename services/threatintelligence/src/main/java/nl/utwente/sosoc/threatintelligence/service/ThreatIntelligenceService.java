package nl.utwente.sosoc.threatintelligence.service;

import jakarta.annotation.PostConstruct;
import nl.utwente.sosoc.threatintelligence.entity.IOCEntity;
import nl.utwente.sosoc.threatintelligence.model.Alarm;
import nl.utwente.sosoc.threatintelligence.model.IOC;
import nl.utwente.sosoc.threatintelligence.model.Severity;
import nl.utwente.sosoc.threatintelligence.model.Threat;
import nl.utwente.sosoc.threatintelligence.repository.IOCRepository;
import nl.utwente.sosoc.threatintelligence.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ThreatIntelligenceService {

    @Autowired private IOCRepository iocRepository;
    @Autowired private EntityMapper entityMapper;

    @PostConstruct
    public void init() {
        iocRepository.save(entityMapper.to(IOCEntity.class).apply(new IOC()
            .threat(new Threat()
                .code("phishing")
                .severity(Severity.HIGH)
                .tactic("ta0001")
                .technique("t1566.001")
            )
            .match("Data['email']['source'].contains('malicious.com')")
        ));
    }

    /**
     * Go over each IOC entry and execute the match expression on the given alarm.
     * Returns the first IOC that matches.
     *
     * @param alarm the alarm
     * @return the matching IOC instance
     */
    public IOC detectIoc(Alarm alarm) {
        ExpressionParser parser = new SpelExpressionParser();

        for (IOCEntity iocEntity : iocRepository.findAll()) {
            IOC ioc = entityMapper.to(IOC.class).apply(iocEntity);
            String matchString = ioc.getMatch();

            if (matchString == null) {
                continue;
            }

            Expression expression = parser.parseExpression(matchString);
            try {
                Boolean result = expression.getValue(alarm, Boolean.class);
                if (result != null && result) {
                    return ioc;
                }
            } catch (EvaluationException ignored) {
            }
        }

        return null;
    }

}
