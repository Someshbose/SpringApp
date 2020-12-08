package github.io.somesh.infra.web;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * ValidateRequestHerader class.
 * 
 * @author sombose
 *
 */
@ExtendWith(MockitoExtension.class)
public class ValidateRequestHeaderTest {

  private ValidateRequestHeaderInterceptor testObject;

  @BeforeEach
  public void setUp() {
    testObject = new ValidateRequestHeaderInterceptor();
  }

  @BeforeEach
  public void tearDown() {
    RequestContext.clearThreadLocalContext();
  }

  @Test
  public void preHandlein_Valid() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addHeader("x-corrId", "S-123");
    request.addHeader("x-appCode", "FLUPLDR");
    
    assertTrue(testObject.preHandle(request, null, null));
    
  }

}
