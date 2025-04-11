package nl.utwente.sosoc.identitymanagement.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import nl.utwente.sosoc.identitymanagement.model.ActionAction;
import nl.utwente.sosoc.identitymanagement.model.User;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Action
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-11T03:18:22.990415900+02:00[Europe/Amsterdam]", comments = "Generator version: 7.12.0")
public class Action {

  private @Nullable ActionAction action;

  private @Nullable User user;

  public Action action(ActionAction action) {
    this.action = action;
    return this;
  }

  /**
   * Get action
   * @return action
   */
  @Valid 
  @Schema(name = "action", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("action")
  public ActionAction getAction() {
    return action;
  }

  public void setAction(ActionAction action) {
    this.action = action;
  }

  public Action user(User user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
   */
  @Valid 
  @Schema(name = "user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Action action = (Action) o;
    return Objects.equals(this.action, action.action) &&
        Objects.equals(this.user, action.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Action {\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

