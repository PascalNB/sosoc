package nl.utwente.sosoc.userinterface.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import nl.utwente.sosoc.userinterface.model.Threat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * IOC
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-11T16:31:18.596481600+02:00[Europe/Amsterdam]", comments = "Generator version: 7.12.0")
public class IOC {

  private @Nullable UUID id;

  private String match;

  private Threat threat;

  public IOC() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public IOC(String match, Threat threat) {
    this.match = match;
    this.threat = threat;
  }

  public IOC id(UUID id) {
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

  public IOC match(String match) {
    this.match = match;
    return this;
  }

  /**
   * Get match
   * @return match
   */
  @NotNull 
  @Schema(name = "match", example = "Data['email']['source'].contains('malicious.com')", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("match")
  public String getMatch() {
    return match;
  }

  public void setMatch(String match) {
    this.match = match;
  }

  public IOC threat(Threat threat) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IOC IOC = (IOC) o;
    return Objects.equals(this.id, IOC.id) &&
        Objects.equals(this.match, IOC.match) &&
        Objects.equals(this.threat, IOC.threat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, match, threat);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IOC {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    match: ").append(toIndentedString(match)).append("\n");
    sb.append("    threat: ").append(toIndentedString(threat)).append("\n");
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

