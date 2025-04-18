package nl.utwente.sosoc.logmonitor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.utwente.sosoc.logmonitor.producers.AlarmsProducer;
import nl.utwente.sosoc.logmonitor.model.Alarm;
import nl.utwente.sosoc.logmonitor.model.IOC;
import nl.utwente.sosoc.logmonitor.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.*;

@Service
public class RuleExecutorService {

    @Autowired private RestTemplateBuilder restTemplateBuilder;
    @Autowired private AlarmsProducer alarmsProducer;
    @Autowired private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> executeQuery(String query) {
        return jdbcTemplate.queryForList(query);
    }

    public void execute(Rule rule) {
        System.out.println("Executing rule: " + rule.getName());

        List<Map<String, Object>> queryResult = executeQuery(rule.getQuery());
        if (queryResult.isEmpty()) {
            return;
        }
        List<List<Map<String, Object>>> groupedResults = groupResults(rule, queryResult);
        List<Alarm> alarms = groupedResults.stream()
            .map(group -> generateAlarm(rule, group))
            .map(this::enrichAlarm)
            .toList();
        for (Alarm alarm : alarms) {
            alarmsProducer.send("alarms", alarm);
        }
    }

    private List<List<Map<String, Object>>> groupResults(Rule rule, List<Map<String, Object>> queryResult) {
        List<List<Map<String, Object>>> groupedResults = new ArrayList<>();

        int index = 0;
        while (index < queryResult.size()) {
            List<Map<String, Object>> group = new ArrayList<>();
            for (int i = 0; i < rule.getGroup() && index < queryResult.size(); i++, index++) {
                group.add(queryResult.get(index));
            }
            groupedResults.add(group);
        }

        return groupedResults;
    }

    private Alarm generateAlarm(Rule rule, List<Map<String, Object>> queryResult) {
        Map<String, Object> alarmData = new HashMap<>();
        for (int i = queryResult.size() - 1; i >= 0; i--) {
            Map<String, Object> resultEntry = queryResult.get(i);
            rule.getFields().forEach(field -> {
                String key = field.getField();
                Object value = resultEntry.get(key);
                if (field.getType() != null) {
                    switch (field.getType()) {
                        case "object" -> {
                            try {
                                value = new ObjectMapper().readValue(value.toString(), Map.class);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                alarmData.put(key, value);
            });
        }

        System.out.println("Alarm data: " + alarmData);

        return new Alarm()
            .id(UUID.randomUUID())
            .timestamp(OffsetDateTime.now())
            .rule(rule)
            .threat(rule.getThreat())
            .data(alarmData);
    }


    private Alarm enrichAlarm(Alarm alarm) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "http://threatintelligence:8080/alarms";
        RequestEntity<Alarm> requestEntity = RequestEntity.post(url).body(alarm);
        IOC ioc;
        try {
            ioc = restTemplate.postForObject(url, requestEntity, IOC.class);
        } catch (Exception ignored) { // catches 404
            ioc = null;
        }

        alarm.setIoc(ioc);
        // Only set threat if IOC are found and severity higher (ordinal lower) or equal to rule threat
        if (ioc != null
            && ioc.getThreat().getSeverity().ordinal() <= alarm.getRule().getThreat().getSeverity().ordinal()) {
            alarm.setThreat(ioc.getThreat());
        }
        return alarm;
    }

}
