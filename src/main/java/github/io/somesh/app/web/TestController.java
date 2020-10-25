package github.io.somesh.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import github.io.somesh.infra.messaging.MessagePublisher;

/**
 * TestController class.
 * 
 * @author sombose
 */
@RestController
public class TestController {

  private final MessagePublisher publisher;

  /**
   * Constructor for TestController.
   * 
   * @param publisher MessagePublisher.
   */
  @Autowired
  public TestController(MessagePublisher publisher) {
    // TODO Auto-generated constructor stub
    this.publisher = publisher;
  }

  /**
   * welcome method.
   * 
   * @return ResponseEntity
   */
  @GetMapping("/hello")
  public ResponseEntity welcome() {
    return ResponseEntity.ok("Welcome aboard");
  }

  /**
   * kafka publisher method.
   * 
   * @param message String
   */
  @GetMapping("/publish/{message}")
  public void publisher(@PathVariable String message) {
    publisher.publish(message);
  }

}
