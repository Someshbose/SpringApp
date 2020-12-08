package github.io.somesh.infra.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * LogRequestInterceptor Test.
 * 
 * @author sombose
 */
public class LogRequestInterceptorTest {

  private LogRequestInterceptor testObject;

  @BeforeEach
  public void setUp() {
    testObject = new LogRequestInterceptor();
  }

  @Test
  public void preHandle() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addParameter("param1", "PARAM");
    assertTrue(testObject.preHandle(request, null, null));
    assertNotNull(request.getAttribute("startTime"));
  }

  @Test
  public void postHandle() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setAttribute("startTime", 123L);
    assertDoesNotThrow(() -> testObject.afterCompletion(request, null, null, null));
  }
}
