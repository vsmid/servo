package hr.vsmid.servo.internal;

import hr.vsmid.servo.WebConfigurer;
import hr.vsmid.servo.WebModule;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@HandlesTypes({WebModule.class})
public class WebModuleInitializer implements ServletContainerInitializer {

  @Override
  public void onStartup(Set<Class<?>> modules, ServletContext context) throws ServletException {
    for (Class<?> module : modules) {
      try {
        WebModule webModule = (WebModule) module.getDeclaredConstructor().newInstance();
        WebConfigurer webConfigurer = new WebConfigurer(context);
        webModule.configure(webConfigurer);
      } catch (InstantiationException
          | IllegalAccessException
          | InvocationTargetException
          | NoSuchMethodException e) {
        throw new ServletException(e);
      }
    }
  }
}
