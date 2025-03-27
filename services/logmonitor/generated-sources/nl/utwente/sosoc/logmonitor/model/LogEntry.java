package nl.utwente.sosoc.logmonitor.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import nl.utwente.sosoc.logmonitor.model.Endpoint;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LogEntry
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T12:41:12.836935800+01:00[Europe/Amsterdam]", comments = "Generator version: 7.11.0")
public class LogEntry {

  @Valid
  private Map<String, Object> data = new HashMap<>();

  private @Nullable Endpoint endpoint;

  private @Nullable String event;

  private @Nullable UUID id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime timestamp;

  public LogEntry data(Map<String, Object> data) {
    this.data = data;
    return this;
  }

  public LogEntry putDataItem(String key, Object dataItem) {
    if (this.data == null) {
      this.data = new HashMap<>();
    }
    this.data.put(key, dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
   */
  
  @Schema(name = "data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("data")
  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  public LogEntry endpoint(Endpoint endpoint) {
    this.endpoint = endpoint;
    return this;
  }

  /**
   * Get endpoint
   * @return endpoint
   */
  @Valid 
  @Schema(name = "endpoint", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("endpoint")
  public Endpoint getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(Endpoint endpoint) {
    this.endpoint = endpoint;
  }

  public LogEntry event(String event) {
    this.event = event;
    return this;
  }

  /**
   * Get event
   * @return event
   */
  
  @Schema(name = "event", example = "email-received", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("event")
  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public LogEntry id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LogEntry timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
   */
  @Valid 
  @Schema(name = "timestamp", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timestamp")
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogEntry logEntry = (LogEntry) o;
    return Objects.equals(this.data, logEntry.data) &&
        Objects.equals(this.endpoint, logEntry.endpoint) &&
        Objects.equals(this.event, logEntry.event) &&
        Objects.equals(this.id, logEntry.id) &&
        Objects.equals(this.timestamp, logEntry.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, endpoint, event, id, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogEntry {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    endpoint: ").append(toIndentedString(endpoint)).append("\n");
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

