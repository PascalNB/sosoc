package nl.utwente.sosoc.userinterface.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-12T03:55:38.149133400+02:00[Europe/Amsterdam]", comments = "Generator version: 7.12.0")
public class ConditionalNext {

  private String condition;

  private String name;

  public ConditionalNext() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ConditionalNext(String condition, String name) {
    this.condition = condition;
    this.name = name;
  }

  public ConditionalNext condition(String condition) {
    this.condition = condition;
    return this;
  }

  /**
   * Get condition
   * @return condition
   */
  @NotNull 
  @Schema(name = "condition", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("condition")
  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public ConditionalNext name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
        Objects.equals(this.name, conditionalNext.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(condition, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConditionalNext {\n");
    sb.append("    condition: ").append(toIndentedString(condition)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

