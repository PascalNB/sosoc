package nl.utwente.sosoc.logmonitor.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import nl.utwente.sosoc.logmonitor.model.IOC;
import nl.utwente.sosoc.logmonitor.model.Rule;
import nl.utwente.sosoc.logmonitor.model.Threat;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-12T04:03:28.164520700+02:00[Europe/Amsterdam]", comments = "Generator version: 7.12.0")
public class Alarm {

  @Valid
  private Map<String, Object> data = new HashMap<>();

  private UUID id;

  private @Nullable IOC ioc;

  private Rule rule;

  private Threat threat;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  public Alarm() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Alarm(Map<String, Object> data, UUID id, Rule rule, Threat threat, OffsetDateTime timestamp) {
    this.data = data;
    this.id = id;
    this.rule = rule;
    this.threat = threat;
    this.timestamp = timestamp;
  }

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
  @NotNull 
  @Schema(name = "data", requiredMode = Schema.RequiredMode.REQUIRED)
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
  @NotNull @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
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
  @NotNull @Valid 
  @Schema(name = "rule", requiredMode = Schema.RequiredMode.REQUIRED)
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
  @NotNull @Valid 
  @Schema(name = "threat", requiredMode = Schema.RequiredMode.REQUIRED)
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
  @NotNull @Valid 
  @Schema(name = "timestamp", requiredMode = Schema.RequiredMode.REQUIRED)
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

