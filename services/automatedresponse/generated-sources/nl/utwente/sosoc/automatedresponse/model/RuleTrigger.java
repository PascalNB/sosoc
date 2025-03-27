package nl.utwente.sosoc.automatedresponse.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RuleTrigger
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-20T00:08:22.550255208+01:00[Europe/Amsterdam]", comments = "Generator version: 7.11.0")
public class RuleTrigger implements PlaybookTrigger {

  private @Nullable UUID rule;

  public RuleTrigger rule(UUID rule) {
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
  public UUID getRule() {
    return rule;
  }

  public void setRule(UUID rule) {
    this.rule = rule;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RuleTrigger ruleTrigger = (RuleTrigger) o;
    return Objects.equals(this.rule, ruleTrigger.rule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rule);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RuleTrigger {\n");
    sb.append("    rule: ").append(toIndentedString(rule)).append("\n");
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

