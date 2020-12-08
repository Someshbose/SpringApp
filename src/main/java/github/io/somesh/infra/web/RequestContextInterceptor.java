package github.io.somesh.infra.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Extracts RequestContext value.
 * 
 * @author sombose
 */
public class RequestContextInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    if (StringUtils.isNoneBlank(request.getHeader(RequestContext.APP_CODE_KEY.value()))
        && StringUtils.isNoneBlank(request.getHeader(RequestContext.CORRELATION_ID_KEY.value()))) {
      RequestContext.put(RequestContext.APP_CODE_KEY, request.getHeader(RequestContext.APP_CODE_KEY.value()));
      RequestContext.put(RequestContext.CORRELATION_ID_KEY,
          request.getHeader(RequestContext.CORRELATION_ID_KEY.value()));
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) {
    RequestContext.clearThreadLocalContext();
  }
}
