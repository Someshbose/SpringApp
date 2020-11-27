package github.io.somesh.infra.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import github.io.somesh.app.shared.exception.FileUploadException;

/**
 * GLobal Exception handler for Rest Apis.
 * 
 * @author sombose
 *
 */
@ControllerAdvice
@EnableWebMvc
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * handleBadRequests method.
   * 
   * @param ex FileUploadException
   * @param request WebRequest
   * @return ResponseEntity<Object>
   */
  @ExceptionHandler({FileUploadException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<Object> handleBadRequests(FileUploadException ex, final WebRequest request) {
    final List<String> errors = new ArrayList<String>();
    final ErrorResponse appError = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST).errors(errors).build();
    return handleExceptionInternal(ex, appError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
