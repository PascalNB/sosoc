package nl.utwente.sosoc.alertnotify.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import nl.utwente.sosoc.alertnotify.model.IOC;
import nl.utwente.sosoc.alertnotify.model.Rule;
import nl.utwente.sosoc.alertnotify.model.Threat;
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
 * Alarm
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-20T00:08:19.327828997+01:00[Europe/Amsterdam]", comments = "Generator version: 7.11.0")
public class Alarm {

  @Valid
  private Map<String, Object> data = new HashMap<>();

  private @Nullable UUID id;

  private @Nullable IOC ioc;

  private @Nullable Rule rule;

  private @Nullable Threat threat;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime timestamp;

  public Alarm data(Map<String, Object> data) {
    this.data = data;
    return this;
  }

  public Alarm putDataItem(String key, Object dataItem) {
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

  public Alarm id(UUID id) {
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

  public Alarm ioc(IOC ioc) {
    this.ioc = ioc;
    return this;
  }

  /**
   * Get ioc
   * @return ioc
   */
  @Valid 
  @Schema(name = "ioc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ioc")
  public IOC getIoc() {
    return ioc;
  }

  public void setIoc(IOC ioc) {
    this.ioc = ioc;
  }

  public Alarm rule(Rule rule) {
    this.rule = rule;
    return this;
  }

  /**
   * Get rule
   * @return rule
   */
  @Valid 
  @Schema(name = "rule", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("rule")
  public Rule getRule() {
    return rule;
  }

  public void setRule(Rule rule) {
    this.rule = rule;
  }

  public Alarm threat(Threat threat) {
    this.threat = threat;
    return this;
  }

  /**
   * Get threat
   * @return threat
   */
  @Valid 
  @Schema(name = "threat", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("threat")
  public Threat getThreat() {
    return threat;
  }

  public void setThreat(Threat threat) {
    this.threat = threat;
  }

  public Alarm timestamp(OffsetDateTime timestamp) {
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
    Alarm alarm = (Alarm) o;
    return Objects.equals(this.data, alarm.data) &&
        Objects.equals(this.id, alarm.id) &&
        Objects.equals(this.ioc, alarm.ioc) &&
        Objects.equals(this.rule, alarm.rule) &&
        Objects.equals(this.threat, alarm.threat) &&
        Objects.equals(this.timestamp, alarm.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, id, ioc, rule, threat, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Alarm {\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ioc: ").append(toIndentedString(ioc)).append("\n");
    sb.append("    rule: ").append(toIndentedString(rule)).append("\n");
    sb.append("    threat: ").append(toIndentedString(threat)).append("\n");
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

