package nl.utwente.sosoc.userinterface.model;

import java.time.OffsetDateTime;

public record PrettyLogEntry(OffsetDateTime time, String event, Endpoint endpoint, String data) {
}
