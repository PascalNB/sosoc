package nl.utwente.sosoc.logmonitor.api;

import nl.utwente.sosoc.logmonitor.entity.LogEntryEntity;
import nl.utwente.sosoc.logmonitor.model.LogEntry;
import nl.utwente.sosoc.logmonitor.util.EntityMapper;
import nl.utwente.sosoc.logmonitor.repository.LogRepository;
import org.modelmapper.spi.DestinationSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
public class LogsController implements LogsApi {

    @Autowired private LogRepository logRepository;
    @Autowired private EntityMapper entityMapper;

    @Override
    public ResponseEntity<LogEntry> getLog(UUID id) {
        return logRepository.findById(id)
            .map(entityMapper.to(LogEntry.class))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<LogEntry>> getLogs(Integer limit) {
        List<LogEntry> logEntries;
        if (limit == null) {
            logEntries = StreamSupport.stream(logRepository.findAll().spliterator(), false)
                .map(entityMapper.to(LogEntry.class))
                .toList();
        } else {
            PageRequest pageRequest = PageRequest.of(0, limit);
            logEntries = StreamSupport.stream(
                    logRepository.findAllByOrderByTimestampDesc(pageRequest).spliterator(),
                    false
                )
                .map(entityMapper.to(LogEntry.class))
                .toList();
        }

        return ResponseEntity.ok(logEntries);
    }

    @Override
    public ResponseEntity<Void> postLog(LogEntry logEntry) {
        logRepository.save(entityMapper.to(
            LogEntryEntity.class,
            (DestinationSetter<LogEntryEntity, UUID>) LogEntryEntity::setId // skip ID
        ).apply(logEntry.timestamp(OffsetDateTime.now())));
        return ResponseEntity.ok().build();
    }

}
