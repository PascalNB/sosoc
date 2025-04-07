package nl.utwente.sosoc.threatintelligence.service;

import jakarta.annotation.PostConstruct;
import nl.utwente.sosoc.threatintelligence.model.Alarm;
import nl.utwente.sosoc.threatintelligence.model.IOC;
import nl.utwente.sosoc.threatintelligence.model.Severity;
import nl.utwente.sosoc.threatintelligence.model.Threat;
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

    private final Map<UUID, IOC> iocs = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        createIoc(new IOC()
            .threat(new Threat()
                .code("phishing")
                .severity(Severity.HIGH)
                .tactic("ta0001")
                .technique("t1566.001")
            )
            .match("Data['email']['source'].contains('malicious.com')")
        );
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

        for (IOC ioc : iocs.values()) {
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

    /**
     * @return a list of all IOCs
     */
    public List<IOC> getIocs() {
        return new ArrayList<>(iocs.values());
    }

    /**
     * Get an IOC entry by ID.
     *
     * @param uuid the id
     * @return the IOC entry
     */
    public IOC getIoc(UUID uuid) {
        return iocs.get(uuid);
    }

    /**
     * Create a new IOC entry with a random UUID.
     *
     * @param ioc the IOC entry
     */
    public void createIoc(IOC ioc) {
        UUID uuid = UUID.randomUUID();
        iocs.put(uuid, ioc.id(uuid));
    }

    /**
     * Update an existing IOC entry by ID.
     *
     * @param uuid the ID
     * @param ioc the IOC instance
     * @return whether the IOC for the given ID was found and updated
     */
    public boolean updateIoc(UUID uuid, IOC ioc) {
        if (iocs.containsKey(uuid)) {
            return false;
        }
        // TODO: properly update
        iocs.put(uuid, ioc);
        return true;
    }

    /**
     * Delete an existing IOC entry by ID.
     *
     * @param uuid the ID
     * @return whether the IOC for the given ID was found and deleted
     */
    public boolean deleteIoc(UUID uuid) {
        return iocs.remove(uuid) != null;
    }

}
