package hr.vsmid.servo;

import static jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic.DENY;
import static jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic.PERMIT;
import static jakarta.servlet.annotation.ServletSecurity.TransportGuarantee.CONFIDENTIAL;
import static jakarta.servlet.annotation.ServletSecurity.TransportGuarantee.NONE;

import jakarta.servlet.HttpConstraintElement;
import jakarta.servlet.HttpMethodConstraintElement;

public class HttpConstraints {

  public static HttpConstraintElement permit(String... roles) {
    return new HttpConstraintElement(PERMIT, NONE, roles);
  }

  public static HttpConstraintElement permitConfidential(String... roles) {
    return new HttpConstraintElement(PERMIT, CONFIDENTIAL, roles);
  }

  public static HttpConstraintElement permitAll() {
    return new HttpConstraintElement(PERMIT);
  }

  public static HttpConstraintElement permitAllConfidential() {
    return new HttpConstraintElement(PERMIT, CONFIDENTIAL);
  }

  public static HttpConstraintElement denyAll() {
    return new HttpConstraintElement(DENY);
  }

  public static HttpConstraintElement denyAllConfidential() {
    return new HttpConstraintElement(DENY, CONFIDENTIAL);
  }

  public static HttpMethodConstraintElement httpGet(HttpConstraintElement constraint) {
    return method("GET", constraint);
  }

  public static HttpMethodConstraintElement httpGet() {
    return httpGet(null);
  }

  public static HttpMethodConstraintElement httpPost(HttpConstraintElement constraint) {
    return method("POST", constraint);
  }

  public static HttpMethodConstraintElement httpPost() {
    return httpPost(null);
  }

  public static HttpMethodConstraintElement httpDelete(HttpConstraintElement constraint) {
    return method("DELETE", constraint);
  }

  public static HttpMethodConstraintElement httpDelete() {
    return httpDelete(null);
  }

  public static HttpMethodConstraintElement httpPut(HttpConstraintElement constraint) {
    return method("PUT", constraint);
  }

  public static HttpMethodConstraintElement httpPut() {
    return httpPut(null);
  }

  public static HttpMethodConstraintElement httpHead(HttpConstraintElement constraint) {
    return method("HEAD", constraint);
  }

  public static HttpMethodConstraintElement httpHead() {
    return httpHead(null);
  }

  public static HttpMethodConstraintElement httpOptions(HttpConstraintElement constraint) {
    return method("OPTIONS", constraint);
  }

  public static HttpMethodConstraintElement httpOptions() {
    return httpOptions(null);
  }

  public static HttpMethodConstraintElement httpTrace(HttpConstraintElement constraint) {
    return method("TRACE", constraint);
  }

  public static HttpMethodConstraintElement httpTrace() {
    return httpTrace(null);
  }

  private static HttpMethodConstraintElement method(String name, HttpConstraintElement constraint) {
    return constraint != null
        ? new HttpMethodConstraintElement(name, constraint)
        : new HttpMethodConstraintElement(name);
  }
}
