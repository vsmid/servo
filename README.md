# S e r v o

_Simple Java library for building Servlet based applications in a declarative and programmatic way._

## Maven dependency
```xml
<dependency>
    <groupId>hr.vsmid</groupId>
    <artifactId>servo</artifactId>
    <version>0.0.1.snapshot</version>
</dependency>
```

## Features
* Out-of-the-box implementation of _jakarta.servlet.ServletContainerInitializer_
* Annotation free _Servlet_, _Filter_, _Listener_ and _ServletContext_ builder-like configurer (see example below)

## Example
```java
import static jakarta.servlet.DispatcherType.REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.EnumSet;

import hr.vsmid.servo.WebModule;
import hr.vsmid.servo.WebConfigurer;

public class WebApp extends WebModule {

  @Override
  protected void configure(WebConfigurer webConfigurer) {
    webConfigurer
        .listener(
            new ServletContextListener() {
              @Override
              public void contextInitialized(ServletContextEvent sce) {
                System.out.println("Context initialized!");
              }
            })
        .filter(
            (request, response, chain) -> {
              System.out.println("filter 1...");
              chain.doFilter(request, response);
            },
            f -> f.addMappingForUrlPatterns(EnumSet.of(REQUEST), true, "/api1/*"))
        .filter(
            (request, response, chain) -> {
              System.out.println("filter 2...");
              chain.doFilter(request, response);
            },
            f -> {
              f.addMappingForUrlPatterns(EnumSet.of(REQUEST), true, "/api2/*");
              f.setAsyncSupported(false);
            })
        .servlet(
            new HttpServlet() {
              @Override
              protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws IOException {
                resp.setStatus(SC_OK);
                resp.getWriter().println("Hello!");
              }
            },
            s -> {
              s.addMapping("/api1/*", "/api2/*");
              s.setAsyncSupported(false);
              s.setLoadOnStartup(1);
            });
  }
}
```