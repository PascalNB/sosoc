package nl.utwente.sosoc.playbookmanagement.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PlaybookTriggerOneOf
 */

@JsonTypeName("Playbook_trigger_oneOf")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-19T22:15:24.388401400+01:00[Europe/Amsterdam]", comments = "Generator version: 7.11.0")
public class PlaybookTriggerOneOf implements PlaybookTrigger {

  private @Nullable String match;

  public PlaybookTriggerOneOf match(String match) {
    this.match = match;
    return this;
  }

  /**
   * Get match
   * @return match
   */
  
  @Schema(name = "match", example = "Threat.Code == 'phishing'", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("match")
  public String getMatch() {
    return match;
  }

  public void setMatch(String match) {
    this.match = match;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlaybookTriggerOneOf playbookTriggerOneOf = (PlaybookTriggerOneOf) o;
    return Objects.equals(this.match, playbookTriggerOneOf.match);
  }

  @Override
  public int hashCode() {
    return Objects.hash(match);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlaybookTriggerOneOf {\n");
    sb.append("    match: ").append(toIndentedString(match)).append("\n");
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

