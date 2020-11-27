package github.io.somesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

/**
 * Main SpringApp APplication.
 * 
 * @author sombose
 */

@Slf4j
@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor")
public class SpringAppApplication {

  /**
   * main method of SpringApp.
   * 
   * @param args String[]
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringAppApplication.class, args);
    log.info("File Uploader Application has started successfully.");
  }

}
