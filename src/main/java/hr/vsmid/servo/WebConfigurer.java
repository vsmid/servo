package hr.vsmid.servo;

import jakarta.servlet.*;
import java.nio.charset.Charset;
import java.util.EventListener;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Fluent {@link ServletContext} configurer. Enables configuring {@link ServletContext} in a
 * builder-like fashion.
 */
public class WebConfigurer {

  private final ServletContext context;

  public WebConfigurer(ServletContext context) {
    this.context = context;
  }

  /**
   * @see ServletContext#setAttribute(String, Object)
   */
  public WebConfigurer attribute(String name, Object value) {
    context.setAttribute(name, value);
    return this;
  }

  /**
   * @see ServletContext#setInitParameter(String, String)
   */
  public WebConfigurer initParam(String name, String value) {
    context.setInitParameter(name, value);
    return this;
  }

  /**
   * @see ServletContext#setSessionTimeout(int)
   */
  public WebConfigurer sessionTimeout(int timeout) {
    context.setSessionTimeout(timeout);
    return this;
  }

  /**
   * @see ServletContext#setSessionTrackingModes(Set)
   */
  public WebConfigurer sessionTrackingModes(SessionTrackingMode... sessionTrackingModes) {
    context.setSessionTrackingModes(Set.of(sessionTrackingModes));
    return this;
  }

  /**
   * @see ServletContext#setRequestCharacterEncoding(String)
   */
  public WebConfigurer requestCharacterEncoding(Charset encoding) {
    context.setRequestCharacterEncoding(encoding.name());
    return this;
  }

  /**
   * @see ServletContext#setResponseCharacterEncoding(String)
   */
  public WebConfigurer responseCharacterEncoding(Charset encoding) {
    context.setResponseCharacterEncoding(encoding.name());
    return this;
  }

  /**
   * @see ServletContext#addJspFile(String, String)
   */
  public WebConfigurer jsp(String servletName, String jspFile) {
    context.addJspFile(servletName, jspFile);
    return this;
  }

  /**
   * @see ServletContext#addListener(Class)
   */
  public WebConfigurer listener(Class<? extends EventListener> listener) {
    context.addListener(listener);
    return this;
  }

  /**
   * @see ServletContext#addListener(EventListener)
   */
  public WebConfigurer listener(EventListener listener) {
    context.addListener(listener);
    return this;
  }

  /**
   * Adds the servlet to this {@link ServletContext}. Servlet's class name is used as a name under
   * which servlet is registered within {@link ServletContext}.
   *
   * @param servlet Servlet instance
   * @param registration {@link Consumer} of {@link ServletRegistration.Dynamic}
   * @return {@link WebConfigurer}
   * @see ServletContext#addServlet(String, Servlet)
   */
  public WebConfigurer servlet(
      Servlet servlet, Consumer<ServletRegistration.Dynamic> registration) {
    return servlet(servlet.getClass().getName(), servlet, registration);
  }

  /**
   * Adds the servlet with the given name to this {@link ServletContext}.
   *
   * @param name Servlet name
   * @param servlet Servlet instance
   * @param registration {@link Consumer} of {@link ServletRegistration.Dynamic}
   * @return {@link WebConfigurer}
   * @see ServletContext#addServlet(String, Servlet)
   */
  public WebConfigurer servlet(
      String name, Servlet servlet, Consumer<ServletRegistration.Dynamic> registration) {

    ServletRegistration.Dynamic dynamic = context.addServlet(name, servlet);
    registration.accept(dynamic);

    return this;
  }

  /**
   * Adds the servlet with the given class type to this {@link ServletContext}. Servlet's class name
   * is used as a name under * which servlet is registered within {@link ServletContext}.
   *
   * @param servlet Servlet class
   * @param registration {@link Consumer} of {@link ServletRegistration.Dynamic}
   * @return {@link WebConfigurer}
   * @see ServletContext#addServlet(String, Class)
   */
  public WebConfigurer servlet(
      Class<? extends Servlet> servlet, Consumer<ServletRegistration.Dynamic> registration) {
    return servlet(servlet.getName(), servlet, registration);
  }

  /**
   * Adds the servlet with the given name and class type to this {@link ServletContext}.
   *
   * @param name Servlet name
   * @param servlet Servlet class
   * @param registration {@link Consumer} of {@link ServletRegistration.Dynamic}
   * @return {@link WebConfigurer}
   * @see ServletContext#addServlet(String, Class)
   */
  public WebConfigurer servlet(
      String name,
      Class<? extends Servlet> servlet,
      Consumer<ServletRegistration.Dynamic> registration) {

    ServletRegistration.Dynamic dynamic = context.addServlet(name, servlet);
    registration.accept(dynamic);

    return this;
  }

  /**
   * Adds the filter instance to this {@link ServletContext}. Filter's class name is used as a name
   * under which filter is registered within {@link ServletContext}.
   *
   * @param filter Filter instance
   * @param registration {@link Consumer} of {@link FilterRegistration.Dynamic}
   * @return {@link WebConfigurer}
   * @see ServletContext#addFilter(String, Filter)
   */
  public WebConfigurer filter(Filter filter, Consumer<FilterRegistration.Dynamic> registration) {
    return filter(filter.getClass().getName(), filter, registration);
  }

  /**
   * Adds the filter instance with the given name to this {@link ServletContext}.
   *
   * @param name Filter name
   * @param filter Filter instance
   * @param registration {@link Consumer} of {@link FilterRegistration.Dynamic}
   * @return {@link WebConfigurer}
   * @see ServletContext#addFilter(String, Filter)
   */
  public WebConfigurer filter(
      String name, Filter filter, Consumer<FilterRegistration.Dynamic> registration) {

    FilterRegistration.Dynamic dynamic = context.addFilter(name, filter);
    registration.accept(dynamic);

    return this;
  }

  /**
   * Adds the filter instance to this {@link ServletContext}. Filter's class name is used as a name
   * under which filter is registered within {@link ServletContext}.
   *
   * @param filter Filter class
   * @param registration {@link Consumer} of {@link FilterRegistration.Dynamic}
   * @return {@link WebConfigurer}
   * @see ServletContext#addFilter(String, Class)
   */
  public WebConfigurer filter(
      Class<? extends Filter> filter, Consumer<FilterRegistration.Dynamic> registration) {
    return filter(filter.getName(), filter, registration);
  }

  /**
   * Adds the filter instance with the given name to this {@link ServletContext}.
   *
   * @param name Filter name
   * @param filter Filter class
   * @param registration {@link Consumer} of {@link FilterRegistration.Dynamic}
   * @return {@link WebConfigurer}
   * @see ServletContext#addFilter(String, Class)
   */
  public WebConfigurer filter(
      String name,
      Class<? extends Filter> filter,
      Consumer<FilterRegistration.Dynamic> registration) {

    FilterRegistration.Dynamic dynamic = context.addFilter(name, filter);
    registration.accept(dynamic);

    return this;
  }
}
