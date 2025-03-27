package nl.utwente.sosoc.automatedresponse.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.UUID;
import nl.utwente.sosoc.automatedresponse.model.ConditionalNext;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-20T00:08:22.550255208+01:00[Europe/Amsterdam]", comments = "Generator version: 7.11.0")
public interface StepNext {
}
