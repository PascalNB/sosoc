package nl.utwente.sosoc.logmonitor.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import nl.utwente.sosoc.logmonitor.model.RuleFieldsInner;
import nl.utwente.sosoc.logmonitor.model.Threat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Rule
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T17:40:40.665444700+01:00[Europe/Amsterdam]", comments = "Generator version: 7.11.0")
public class Rule {

  @Valid
  private List<@Valid RuleFieldsInner> fields = new ArrayList<>();

  private @Nullable UUID id;

  private @Nullable String interval;

  private @Nullable String name;

  private @Nullable String query;

  private @Nullable Threat threat;

  private @Nullable BigDecimal threshold;

  public Rule fields(List<@Valid RuleFieldsInner> fields) {
    this.fields = fields;
    return this;
  }

  public Rule addFieldsItem(RuleFieldsInner fieldsItem) {
    if (this.fields == null) {
      this.fields = new ArrayList<>();
    }
    this.fields.add(fieldsItem);
    return this;
  }

  /**
   * Get fields
   * @return fields
   */
  @Valid 
  @Schema(name = "fields", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fields")
  public List<@Valid RuleFieldsInner> getFields() {
    return fields;
  }

  public void setFields(List<@Valid RuleFieldsInner> fields) {
    this.fields = fields;
  }

  public Rule id(UUID id) {
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

  public Rule interval(String interval) {
    this.interval = interval;
    return this;
  }

  /**
   * Get interval
   * @return interval
   */
  
  @Schema(name = "interval", example = "*_/5 * * * *", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("interval")
  public String getInterval() {
    return interval;
  }

  public void setInterval(String interval) {
    this.interval = interval;
  }

  public Rule name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", example = "external email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Rule query(String query) {
    this.query = query;
    return this;
  }

  /**
   * Get query
   * @return query
   */
  
  @Schema(name = "query", example = "SELECT * FROM logs WHERE type='email-received' AND data->'email'->>'source' NOT LIKE %example.com AND timestamp >= NOW() - INTERVAL '5 minutes'; ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("query")
  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public Rule threat(Threat threat) {
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

  public Rule threshold(BigDecimal threshold) {
    this.threshold = threshold;
    return this;
  }

  /**
   * Get threshold
   * minimum: 1
   * @return threshold
   */
  @Valid @DecimalMin("1") 
  @Schema(name = "threshold", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("threshold")
  public BigDecimal getThreshold() {
    return threshold;
  }

  public void setThreshold(BigDecimal threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rule rule = (Rule) o;
    return Objects.equals(this.fields, rule.fields) &&
        Objects.equals(this.id, rule.id) &&
        Objects.equals(this.interval, rule.interval) &&
        Objects.equals(this.name, rule.name) &&
        Objects.equals(this.query, rule.query) &&
        Objects.equals(this.threat, rule.threat) &&
        Objects.equals(this.threshold, rule.threshold);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fields, id, interval, name, query, threat, threshold);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rule {\n");
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    interval: ").append(toIndentedString(interval)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
    sb.append("    threat: ").append(toIndentedString(threat)).append("\n");
    sb.append("    threshold: ").append(toIndentedString(threshold)).append("\n");
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

