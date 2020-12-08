package github.io.somesh.infra.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * Validation of HeaderRequest.
 * 
 * @author sombose
 */
@Slf4j
public class ValidateRequestHeaderInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    if (!StringUtils.isNotBlank(request.getHeader(RequestContext.APP_CODE_KEY.value()))
        || !StringUtils.isNotBlank(request.getHeader(RequestContext.CORRELATION_ID_KEY.value()))) {

      log.error("Mandatory header parameter(s) not received in Request.");
      throw new IllegalArgumentException("Mandatory header parameter(s) not received in Request.");
    }
    return true;
  }

}
