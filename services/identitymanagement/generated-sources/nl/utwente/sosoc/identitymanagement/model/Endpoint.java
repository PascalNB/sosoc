package nl.utwente.sosoc.identitymanagement.model;

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
 * Endpoint
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-12T01:47:00.931679200+02:00[Europe/Amsterdam]", comments = "Generator version: 7.12.0")
public class Endpoint {

  private @Nullable String host;

  private String ip;

  private String name;

  public Endpoint() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Endpoint(String ip, String name) {
    this.ip = ip;
    this.name = name;
  }

  public Endpoint host(String host) {
    this.host = host;
    return this;
  }

  /**
   * Get host
   * @return host
   */
  
  @Schema(name = "host", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("host")
  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Endpoint ip(String ip) {
    this.ip = ip;
    return this;
  }

  /**
   * Get ip
   * @return ip
   */
  @NotNull 
  @Schema(name = "ip", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ip")
  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public Endpoint name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "client-1", requiredMode = Schema.RequiredMode.REQUIRED)
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
    Endpoint endpoint = (Endpoint) o;
    return Objects.equals(this.host, endpoint.host) &&
        Objects.equals(this.ip, endpoint.ip) &&
        Objects.equals(this.name, endpoint.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(host, ip, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Endpoint {\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
    sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
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

