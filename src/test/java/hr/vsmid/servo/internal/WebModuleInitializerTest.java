package hr.vsmid.servo.internal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hr.vsmid.servo.WebConfigurer;
import hr.vsmid.servo.WebModule;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.http.HttpServlet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WebModuleInitializerTest {

  static class MyServlet extends HttpServlet {}

  static class WebApp extends WebModule {
    @Override
    public void configure(WebConfigurer webConfigurer) {
      webConfigurer.servlet("MyServlet", myServlet, servlet -> servlet.addMapping("/*"));
    }
  }

  private static final MyServlet myServlet = new MyServlet();

  @Test
  void onStartup(
      @Mock ServletContext servletContext, @Mock ServletRegistration.Dynamic registration)
      throws ServletException {
    when(servletContext.addServlet(anyString(), eq(myServlet))).thenReturn(registration);

    WebModuleInitializer webModuleInitializer = new WebModuleInitializer();
    webModuleInitializer.onStartup(Set.of(WebApp.class), servletContext);

    verify(servletContext, times(1)).addServlet("MyServlet", myServlet);
  }
}
