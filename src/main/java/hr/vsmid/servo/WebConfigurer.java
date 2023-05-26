package hr.vsmid.servo;

import jakarta.servlet.*;
import java.nio.charset.Charset;
import java.util.EventListener;
import java.util.Set;
import java.util.function.Consumer;

public class WebConfigurer {

  private final ServletContext context;

  public WebConfigurer(ServletContext context) {
    this.context = context;
  }

  public WebConfigurer attribute(String name, Object value) {
    context.setAttribute(name, value);
    return this;
  }

  public WebConfigurer initParam(String name, String value) {
    context.setInitParameter(name, value);
    return this;
  }

  public WebConfigurer sessionTimeout(int timeout) {
    context.setSessionTimeout(timeout);
    return this;
  }

  public WebConfigurer sessionTrackingMode(SessionTrackingMode... sessionTrackingModes) {
    context.setSessionTrackingModes(Set.of(sessionTrackingModes));
    return this;
  }

  public WebConfigurer requestCharacterEncoding(Charset encoding) {
    context.setRequestCharacterEncoding(encoding.name());
    return this;
  }

  public WebConfigurer responseCharacterEncoding(Charset encoding) {
    context.setResponseCharacterEncoding(encoding.name());
    return this;
  }

  public WebConfigurer jsp(String servletName, String jspFile) {
    context.addJspFile(servletName, jspFile);
    return this;
  }

  public WebConfigurer listener(Class<? extends EventListener> listener) {
    context.addListener(listener);
    return this;
  }

  public WebConfigurer listener(EventListener listener) {
    context.addListener(listener);
    return this;
  }

  public WebConfigurer servlet(
      Servlet servlet, Consumer<ServletRegistration.Dynamic> registration) {
    return servlet(servlet.getClass().getName(), servlet, registration);
  }

  public WebConfigurer servlet(
      String name, Servlet servlet, Consumer<ServletRegistration.Dynamic> registration) {

    ServletRegistration.Dynamic dynamic = context.addServlet(name, servlet);
    registration.accept(dynamic);

    return this;
  }

  public WebConfigurer servlet(
      Class<? extends Servlet> servlet, Consumer<ServletRegistration.Dynamic> registration) {
    return servlet(servlet.getName(), servlet, registration);
  }

  public WebConfigurer servlet(
      String name,
      Class<? extends Servlet> servlet,
      Consumer<ServletRegistration.Dynamic> registration) {

    ServletRegistration.Dynamic dynamic = context.addServlet(name, servlet);
    registration.accept(dynamic);

    return this;
  }

  public WebConfigurer filter(Filter filter, Consumer<FilterRegistration.Dynamic> registration) {
    return filter(filter.getClass().getName(), filter, registration);
  }

  public WebConfigurer filter(
      String name, Filter filter, Consumer<FilterRegistration.Dynamic> registration) {

    FilterRegistration.Dynamic dynamic = context.addFilter(name, filter);
    registration.accept(dynamic);

    return this;
  }

  public WebConfigurer filter(
      Class<? extends Filter> filter, Consumer<FilterRegistration.Dynamic> registration) {
    return filter(filter.getName(), filter, registration);
  }

  public WebConfigurer filter(
      String name,
      Class<? extends Filter> filter,
      Consumer<FilterRegistration.Dynamic> registration) {

    FilterRegistration.Dynamic dynamic = context.addFilter(name, filter);
    registration.accept(dynamic);

    return this;
  }
}
