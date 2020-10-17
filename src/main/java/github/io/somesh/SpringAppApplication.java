package github.io.somesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main SpringApp APplication.
 * 
 * @author sombose
 */

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

  }

}
