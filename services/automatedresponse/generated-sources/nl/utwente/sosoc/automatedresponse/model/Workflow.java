package nl.utwente.sosoc.automatedresponse.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import nl.utwente.sosoc.automatedresponse.model.Alarm;
import nl.utwente.sosoc.automatedresponse.model.Playbook;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Workflow
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-12T01:46:58.689696100+02:00[Europe/Amsterdam]", comments = "Generator version: 7.12.0")
public class Workflow {

  private Alarm alarm;

  private UUID id;

  private @Nullable String nextStep;

  private Playbook playbook;

  public Workflow() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Workflow(Alarm alarm, UUID id, Playbook playbook) {
    this.alarm = alarm;
    this.id = id;
    this.playbook = playbook;
  }

  public Workflow alarm(Alarm alarm) {
    this.alarm = alarm;
    return this;
  }

  /**
   * Get alarm
   * @return alarm
   */
  @NotNull @Valid 
  @Schema(name = "alarm", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("alarm")
  public Alarm getAlarm() {
    return alarm;
  }

  public void setAlarm(Alarm alarm) {
    this.alarm = alarm;
  }

  public Workflow id(UUID id) {
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

  public Workflow nextStep(String nextStep) {
    this.nextStep = nextStep;
    return this;
  }

  /**
   * Get nextStep
   * @return nextStep
   */
  
  @Schema(name = "nextStep", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nextStep")
  public String getNextStep() {
    return nextStep;
  }

  public void setNextStep(String nextStep) {
    this.nextStep = nextStep;
  }

  public Workflow playbook(Playbook playbook) {
    this.playbook = playbook;
    return this;
  }

  /**
   * Get playbook
   * @return playbook
   */
  @NotNull @Valid 
  @Schema(name = "playbook", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("playbook")
  public Playbook getPlaybook() {
    return playbook;
  }

  public void setPlaybook(Playbook playbook) {
    this.playbook = playbook;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Workflow workflow = (Workflow) o;
    return Objects.equals(this.alarm, workflow.alarm) &&
        Objects.equals(this.id, workflow.id) &&
        Objects.equals(this.nextStep, workflow.nextStep) &&
        Objects.equals(this.playbook, workflow.playbook);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alarm, id, nextStep, playbook);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Workflow {\n");
    sb.append("    alarm: ").append(toIndentedString(alarm)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nextStep: ").append(toIndentedString(nextStep)).append("\n");
    sb.append("    playbook: ").append(toIndentedString(playbook)).append("\n");
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

