package hr.vsmid.servo;

import static jakarta.servlet.SessionTrackingMode.*;
import static org.mockito.Mockito.*;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.http.HttpServlet;
import java.nio.charset.Charset;
import java.util.EventListener;
import java.util.Set;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WebConfigurerTest {

  WebConfigurer webConfigurer;
  ServletContext servletContext;

  @BeforeEach
  void init(@Mock ServletContext servletContext) {
    this.servletContext = servletContext;
    this.webConfigurer = new WebConfigurer(servletContext);
  }

  @Test
  void attribute() {
    webConfigurer.attribute("name", "value");

    verify(servletContext, times(1)).setAttribute("name", "value");
  }

  @Test
  void initParam() {
    webConfigurer.initParam("name", "value");

    verify(servletContext, times(1)).setInitParameter("name", "value");
  }

  @Test
  void sessionTimeout() {
    webConfigurer.sessionTimeout(1);

    verify(servletContext, times(1)).setSessionTimeout(1);
  }

  @Test
  void sessionTrackingMode() {
    webConfigurer.sessionTrackingModes(COOKIE);

    verify(servletContext, times(1)).setSessionTrackingModes(Set.of(COOKIE));
  }

  @Test
  void requestCharacterEncoding() {
    webConfigurer.requestCharacterEncoding(Charset.defaultCharset());

    verify(servletContext, times(1)).setRequestCharacterEncoding(Charset.defaultCharset().name());
  }

  @Test
  void responseCharacterEncoding() {
    webConfigurer.responseCharacterEncoding(Charset.defaultCharset());

    verify(servletContext, times(1)).setResponseCharacterEncoding(Charset.defaultCharset().name());
  }

  @Test
  void jsp() {
    webConfigurer.jsp("JspServlet", "file.jsp");

    verify(servletContext, times(1)).addJspFile("JspServlet", "file.jsp");
  }

  @Test
  void listener(@Mock EventListener eventListener) {
    webConfigurer.listener(eventListener);

    verify(servletContext, times(1)).addListener(eventListener);
  }

  @Test
  void listenerFromClass() {
    webConfigurer.listener(EventListener.class);

    verify(servletContext, times(1)).addListener(EventListener.class);
  }

  @Test
  void servlet(
      @Mock HttpServlet httpServlet, @Mock Consumer<ServletRegistration.Dynamic> registration) {
    webConfigurer.servlet("MyServlet", httpServlet, registration);

    verify(servletContext, times(1)).addServlet("MyServlet", httpServlet);
  }

  @Test
  void servletFromClass(@Mock Consumer<ServletRegistration.Dynamic> registration) {
    webConfigurer.servlet("MyServlet", HttpServlet.class, registration);

    verify(servletContext, times(1)).addServlet("MyServlet", HttpServlet.class);
  }

  @Test
  void servletWithClassName(
      @Mock HttpServlet httpServlet, @Mock Consumer<ServletRegistration.Dynamic> registration) {
    webConfigurer.servlet(httpServlet, registration);

    verify(servletContext, times(1)).addServlet(httpServlet.getClass().getName(), httpServlet);
  }

  @Test
  void servletWithClassNameFromClass(@Mock Consumer<ServletRegistration.Dynamic> registration) {
    webConfigurer.servlet(HttpServlet.class, registration);

    verify(servletContext, times(1)).addServlet(HttpServlet.class.getName(), HttpServlet.class);
  }

  @Test
  void filter(@Mock Filter filter, @Mock Consumer<FilterRegistration.Dynamic> registration) {
    webConfigurer.filter("MyFilter", filter, registration);

    verify(servletContext, times(1)).addFilter("MyFilter", filter);
  }

  @Test
  void filterFromClass(@Mock Consumer<FilterRegistration.Dynamic> registration) {
    webConfigurer.filter("MyFilter", Filter.class, registration);

    verify(servletContext, times(1)).addFilter("MyFilter", Filter.class);
  }

  @Test
  void filterWithClassName(
      @Mock Filter filter, @Mock Consumer<FilterRegistration.Dynamic> registration) {
    webConfigurer.filter(filter, registration);

    verify(servletContext, times(1)).addFilter(filter.getClass().getName(), filter);
  }

  @Test
  void filterWithClassNameFromClass(@Mock Consumer<FilterRegistration.Dynamic> registration) {
    webConfigurer.filter(Filter.class, registration);

    verify(servletContext, times(1)).addFilter(Filter.class.getName(), Filter.class);
  }
}
