package github.io.somesh.infra.web;

import org.slf4j.MDC;

/**
 * RequestContext Enum.
 * 
 * @author sombose
 */
public enum RequestContext {
  //@formatter:off
  CORRELATION_ID_KEY("x-corrId"),
  APP_CODE_KEY("x-appCode");
  //@formatter:on

  private final String value;

  /**
   * Constructor of RequestContext.
   * 
   * @param value String
   */
  RequestContext(String value) {
    this.value = value;
  }

  /**
   * method returning value.
   * 
   * @return String
   */
  public String value() {
    return this.value;
  }

  /**
   * static put method
   * 
   * @param contextKey RequestContext
   * @param value String
   */
  public static void put(RequestContext contextKey, String value) {
    MDC.put(contextKey.value(), value);
  }

  /**
   * static get method
   * 
   * @param contextKey RequestContext
   * @return String
   */
  public static String get(RequestContext contextKey) {
    return MDC.get(contextKey.value());
  }

  /**
   * 
   */
  public static void clearThreadLocalContext() {
    MDC.clear();
  }
}
