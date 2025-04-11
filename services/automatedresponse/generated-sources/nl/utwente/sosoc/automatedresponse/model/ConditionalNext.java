package nl.utwente.sosoc.automatedresponse.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ConditionalNext
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-11T15:54:48.476034900+02:00[Europe/Amsterdam]", comments = "Generator version: 7.12.0")
public class ConditionalNext {

  private @Nullable String condition;

  private @Nullable UUID next;

  public ConditionalNext condition(String condition) {
    this.condition = condition;
    return this;
  }

  /**
   * Get condition
   * @return condition
   */
  
  @Schema(name = "condition", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("condition")
  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public ConditionalNext next(UUID next) {
    this.next = next;
    return this;
  }

  /**
   * Get next
   * @return next
   */
  @Valid 
  @Schema(name = "next", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("next")
  public UUID getNext() {
    return next;
  }

  public void setNext(UUID next) {
    this.next = next;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConditionalNext conditionalNext = (ConditionalNext) o;
    return Objects.equals(this.condition, conditionalNext.condition) &&
        Objects.equals(this.next, conditionalNext.next);
  }

  @Override
  public int hashCode() {
    return Objects.hash(condition, next);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConditionalNext {\n");
    sb.append("    condition: ").append(toIndentedString(condition)).append("\n");
    sb.append("    next: ").append(toIndentedString(next)).append("\n");
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

