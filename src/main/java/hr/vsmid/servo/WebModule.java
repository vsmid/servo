package hr.vsmid.servo;

/** Class type handled by {@link hr.vsmid.servo.internal.WebModuleInitializer}. */
public abstract class WebModule {

  /**
   * Method which is internally called by the {@link hr.vsmid.servo.internal.WebModuleInitializer}
   * to register listeners, filters, servlets etc.
   *
   * @param webConfigurer Fluent {@link jakarta.servlet.ServletContext} configurer.
   */
  public abstract void configure(WebConfigurer webConfigurer);
}
