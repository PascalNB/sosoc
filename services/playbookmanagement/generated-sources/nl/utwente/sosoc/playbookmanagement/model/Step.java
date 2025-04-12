package nl.utwente.sosoc.playbookmanagement.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nl.utwente.sosoc.playbookmanagement.model.ConditionalNext;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Step
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-12T01:47:05.623188300+02:00[Europe/Amsterdam]", comments = "Generator version: 7.12.0")
public class Step {

  private String action;

  private String name;

  @Valid
  private List<@Valid ConditionalNext> next = new ArrayList<>();

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    HUMAN("human"),
    
    AUTO("auto");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  public Step() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Step(String action, String name, List<@Valid ConditionalNext> next, TypeEnum type) {
    this.action = action;
    this.name = name;
    this.next = next;
    this.type = type;
  }

  public Step action(String action) {
    this.action = action;
    return this;
  }

  /**
   * Get action
   * @return action
   */
  @NotNull 
  @Schema(name = "action", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("action")
  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public Step name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "await analyst confirmation", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Step next(List<@Valid ConditionalNext> next) {
    this.next = next;
    return this;
  }

  public Step addNextItem(ConditionalNext nextItem) {
    if (this.next == null) {
      this.next = new ArrayList<>();
    }
    this.next.add(nextItem);
    return this;
  }

  /**
   * Get next
   * @return next
   */
  @NotNull @Valid 
  @Schema(name = "next", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("next")
  public List<@Valid ConditionalNext> getNext() {
    return next;
  }

  public void setNext(List<@Valid ConditionalNext> next) {
    this.next = next;
  }

  public Step type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @NotNull 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Step step = (Step) o;
    return Objects.equals(this.action, step.action) &&
        Objects.equals(this.name, step.name) &&
        Objects.equals(this.next, step.next) &&
        Objects.equals(this.type, step.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, name, next, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Step {\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    next: ").append(toIndentedString(next)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

