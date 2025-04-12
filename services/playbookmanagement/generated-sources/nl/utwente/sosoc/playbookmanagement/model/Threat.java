package nl.utwente.sosoc.playbookmanagement.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import nl.utwente.sosoc.playbookmanagement.model.Severity;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Threat
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-12T01:47:05.623188300+02:00[Europe/Amsterdam]", comments = "Generator version: 7.12.0")
public class Threat {

  private String code;

  private Severity severity;

  private @Nullable String tactic;

  private @Nullable String technique;

  public Threat() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Threat(String code, Severity severity) {
    this.code = code;
    this.severity = severity;
  }

  public Threat code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   */
  @NotNull 
  @Schema(name = "code", example = "phishing", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Threat severity(Severity severity) {
    this.severity = severity;
    return this;
  }

  /**
   * Get severity
   * @return severity
   */
  @NotNull @Valid 
  @Schema(name = "severity", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("severity")
  public Severity getSeverity() {
    return severity;
  }

  public void setSeverity(Severity severity) {
    this.severity = severity;
  }

  public Threat tactic(String tactic) {
    this.tactic = tactic;
    return this;
  }

  /**
   * Get tactic
   * @return tactic
   */
  
  @Schema(name = "tactic", example = "ta0001", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tactic")
  public String getTactic() {
    return tactic;
  }

  public void setTactic(String tactic) {
    this.tactic = tactic;
  }

  public Threat technique(String technique) {
    this.technique = technique;
    return this;
  }

  /**
   * Get technique
   * @return technique
   */
  
  @Schema(name = "technique", example = "t1566.001", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("technique")
  public String getTechnique() {
    return technique;
  }

  public void setTechnique(String technique) {
    this.technique = technique;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Threat threat = (Threat) o;
    return Objects.equals(this.code, threat.code) &&
        Objects.equals(this.severity, threat.severity) &&
        Objects.equals(this.tactic, threat.tactic) &&
        Objects.equals(this.technique, threat.technique);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, severity, tactic, technique);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Threat {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    severity: ").append(toIndentedString(severity)).append("\n");
    sb.append("    tactic: ").append(toIndentedString(tactic)).append("\n");
    sb.append("    technique: ").append(toIndentedString(technique)).append("\n");
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

