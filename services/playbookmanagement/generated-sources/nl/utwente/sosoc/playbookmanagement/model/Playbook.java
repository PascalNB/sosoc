package nl.utwente.sosoc.playbookmanagement.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import nl.utwente.sosoc.playbookmanagement.model.PlaybookTrigger;
import nl.utwente.sosoc.playbookmanagement.model.Step;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Playbook
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-20T00:08:31.704882963+01:00[Europe/Amsterdam]", comments = "Generator version: 7.11.0")
public class Playbook {

  private @Nullable String description;

  private @Nullable UUID firstStep;

  private @Nullable UUID id;

  private @Nullable String name;

  @Valid
  private List<@Valid Step> steps = new ArrayList<>();

  private @Nullable PlaybookTrigger trigger;

  public Playbook description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", example = "quarantines endpoint", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Playbook firstStep(UUID firstStep) {
    this.firstStep = firstStep;
    return this;
  }

  /**
   * Get firstStep
   * @return firstStep
   */
  @Valid 
  @Schema(name = "firstStep", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firstStep")
  public UUID getFirstStep() {
    return firstStep;
  }

  public void setFirstStep(UUID firstStep) {
    this.firstStep = firstStep;
  }

  public Playbook id(UUID id) {
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

  public Playbook name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", example = "phishing email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Playbook steps(List<@Valid Step> steps) {
    this.steps = steps;
    return this;
  }

  public Playbook addStepsItem(Step stepsItem) {
    if (this.steps == null) {
      this.steps = new ArrayList<>();
    }
    this.steps.add(stepsItem);
    return this;
  }

  /**
   * Get steps
   * @return steps
   */
  @Valid @Size(min = 1) 
  @Schema(name = "steps", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("steps")
  public List<@Valid Step> getSteps() {
    return steps;
  }

  public void setSteps(List<@Valid Step> steps) {
    this.steps = steps;
  }

  public Playbook trigger(PlaybookTrigger trigger) {
    this.trigger = trigger;
    return this;
  }

  /**
   * Get trigger
   * @return trigger
   */
  @Valid 
  @Schema(name = "trigger", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("trigger")
  public PlaybookTrigger getTrigger() {
    return trigger;
  }

  public void setTrigger(PlaybookTrigger trigger) {
    this.trigger = trigger;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Playbook playbook = (Playbook) o;
    return Objects.equals(this.description, playbook.description) &&
        Objects.equals(this.firstStep, playbook.firstStep) &&
        Objects.equals(this.id, playbook.id) &&
        Objects.equals(this.name, playbook.name) &&
        Objects.equals(this.steps, playbook.steps) &&
        Objects.equals(this.trigger, playbook.trigger);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, firstStep, id, name, steps, trigger);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Playbook {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    firstStep: ").append(toIndentedString(firstStep)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    steps: ").append(toIndentedString(steps)).append("\n");
    sb.append("    trigger: ").append(toIndentedString(trigger)).append("\n");
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

