<p>
  <h2 style="text-align: center">S e r v o</h2>
</p>

_Simple Java library for building Servlet based applications in a declarative and programmatic way._

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=vsmid_servo&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=vsmid_servo)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=vsmid_servo&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=vsmid_servo)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=vsmid_servo&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=vsmid_servo)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=vsmid_servo&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=vsmid_servo)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=vsmid_servo&metric=coverage)](https://sonarcloud.io/summary/new_code?id=vsmid_servo)

## Features

* Out-of-the-box implementation of _jakarta.servlet.ServletContainerInitializer_
* Annotation free _Servlet_, _Filter_, _Listener_ and _ServletContext_ builder-like configurer (see example below)
* A set of predefined `jakarta.servlet.HttpConstraintElement`and `jakarta.servlet.HttpMethodConstraintElement`
  constraints to reduce verbosity and improve readability when setting servlet security

## Maven dependency

This dependency has not yet been published to any public Maven repository, so before you can use it you should
install it to your local Maven repository.

### Build & install

In the root directory of this project run the following Maven command:

```bash 
mvn clean install
```

### Maven coordinates

```xml
<dependency>
    <groupId>hr.vsmid</groupId>
    <artifactId>servo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Example

```java
import static hr.vsmid.servo.HttpConstraints.denyAll;
import static hr.vsmid.servo.HttpConstraints.httpDelete;
import static hr.vsmid.servo.HttpConstraints.httpGet;
import static hr.vsmid.servo.HttpConstraints.httpPost;
import static hr.vsmid.servo.HttpConstraints.permit;
import static hr.vsmid.servo.HttpConstraints.permitAll;
import static hr.vsmid.servo.HttpConstraints.permitConfidential;

import hr.vsmid.servo.WebConfigurer;
import hr.vsmid.servo.WebModule;

import static jakarta.servlet.DispatcherType.REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

public class WebApp extends WebModule {

  @Override
  public void configure(WebConfigurer webConfigurer) {
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
            filter -> filter.addMappingForUrlPatterns(EnumSet.of(REQUEST), true, "/api1/*"))
        .filter(
            (request, response, chain) -> {
              System.out.println("filter 2...");
              chain.doFilter(request, response);
            },
            filter -> {
              filter.addMappingForUrlPatterns(EnumSet.of(REQUEST), true, "/api2/*");
              filter.setAsyncSupported(false);
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
            servlet -> {
              servlet.addMapping("/api1/*", "/api2/*");
              servlet.setAsyncSupported(false);
              servlet.setLoadOnStartup(1);
            })
        .servlet(
            "SecureServlet",
            new HttpServlet() {
              @Override
              protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                  throws IOException {
                resp.setStatus(SC_OK);
                resp.getWriter().println("Hello from secured servlet!");
              }
            },
            servlet -> {
              servlet.addMapping("/api3/*");
              servlet.setServletSecurity(
                  new ServletSecurityElement(
                      denyAll(),
                      List.of(
                          httpGet(permitAll()),
                          httpPost(permit("admin")),
                          httpDelete(permitConfidential("admin")))));
            });
  }
}
```