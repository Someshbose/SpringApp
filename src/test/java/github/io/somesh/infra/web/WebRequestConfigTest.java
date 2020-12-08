package github.io.somesh.infra.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * WebRequest COnfiguration test.
 * 
 * @author sombose
 */
@ExtendWith(MockitoExtension.class)
public class WebRequestConfigTest {

  private WebRequestConfig testObject;

  @Mock
  private InterceptorRegistry registry;

  @BeforeEach
  public void setUp() {
    testObject = new WebRequestConfig();
  }

  @Test
  public void addInterceptor() {
    InterceptorRegistration registration1 = Mockito.mock(InterceptorRegistration.class);
    Mockito.when(registry.addInterceptor(Mockito.any(ValidateRequestHeaderInterceptor.class)))
        .thenReturn(registration1);
    InterceptorRegistration registration2 = Mockito.mock(InterceptorRegistration.class);
    Mockito.when(registry.addInterceptor(Mockito.any(RequestContextInterceptor.class))).thenReturn(registration2);
    InterceptorRegistration registration3 = Mockito.mock(InterceptorRegistration.class);
    Mockito.when(registry.addInterceptor(Mockito.any(LogRequestInterceptor.class))).thenReturn(registration3);

    testObject.addInterceptors(registry);

    Mockito.verify(registration1).addPathPatterns("/**");
    Mockito.verify(registration2).addPathPatterns("/**");
    Mockito.verify(registration3).addPathPatterns("/**");

  }
}
