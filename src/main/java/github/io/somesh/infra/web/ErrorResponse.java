package github.io.somesh.infra.web;

import java.util.List;
import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Data;

/**
 * Object representing and Error to be sent to the API caller incase of an Error.
 * 
 * @author sombose
 */

@Data
@Builder
public class ErrorResponse {
  private final HttpStatus status;
  private final List<String> errors;

  private String description;
}
