package github.io.somesh.infra.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import lombok.extern.slf4j.Slf4j;

/**
 * WebRequest Configuration.
 * 
 * @author sombose
 */
@Configuration
@Slf4j
public class WebRequestConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    log.info("File Uploaded started{}.", this.getClass().getSimpleName());
    registry.addInterceptor(new ValidateRequestHeaderInterceptor()).addPathPatterns("/**");
    registry.addInterceptor(new RequestContextInterceptor()).addPathPatterns("/**");
    registry.addInterceptor(new LogRequestInterceptor()).addPathPatterns("/**");
    log.info("Resgistered{}.", this.getClass().getSimpleName());
  }
}
