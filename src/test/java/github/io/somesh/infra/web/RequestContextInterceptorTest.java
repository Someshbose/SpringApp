package github.io.somesh.infra.web;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * RequestContextInterceptor Test.
 * 
 * @author sombose
 */

@ExtendWith(MockitoExtension.class)
public class RequestContextInterceptorTest {

  private RequestContextInterceptor testObject;

  @BeforeEach
  public void setUp() {
    testObject = new RequestContextInterceptor();
    RequestContext.clearThreadLocalContext();
  }

  @BeforeEach
  public void tearDown() {
    RequestContext.clearThreadLocalContext();
  }

  @Test
  public void preHandleinValidAppCode() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addHeader("x-corrId", "S-123");
    assertTrue(testObject.preHandle(request, null, null));
    assertNull(RequestContext.get(RequestContext.CORRELATION_ID_KEY));
  }

}
