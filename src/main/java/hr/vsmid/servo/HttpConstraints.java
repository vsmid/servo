package hr.vsmid.servo;

import static jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic.DENY;
import static jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic.PERMIT;
import static jakarta.servlet.annotation.ServletSecurity.TransportGuarantee.CONFIDENTIAL;
import static jakarta.servlet.annotation.ServletSecurity.TransportGuarantee.NONE;

import jakarta.servlet.HttpConstraintElement;
import jakarta.servlet.HttpMethodConstraintElement;

/**
 * A set of predefined {@link jakarta.servlet.HttpConstraintElement} and {@link
 * jakarta.servlet.HttpMethodConstraintElement} constraints to reduce verbosity and improve
 * readability when setting servlet security.
 */
public class HttpConstraints {

  /**
   * Assumes {@link jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic#PERMIT} and {@link
   * jakarta.servlet.annotation.ServletSecurity.TransportGuarantee#NONE}.
   *
   * @param roles Allow access to this roles
   * @return {@link HttpConstraintElement}
   */
  public static HttpConstraintElement permit(String... roles) {
    return new HttpConstraintElement(PERMIT, NONE, roles);
  }

  /**
   * Assumes {@link jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic#PERMIT} and {@link
   * jakarta.servlet.annotation.ServletSecurity.TransportGuarantee#CONFIDENTIAL}.
   *
   * @param roles Allow access to this roles
   * @return {@link HttpConstraintElement}
   */
  public static HttpConstraintElement permitConfidential(String... roles) {
    return new HttpConstraintElement(PERMIT, CONFIDENTIAL, roles);
  }

  /**
   * Assumes {@link jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic#PERMIT}, {@link
   * jakarta.servlet.annotation.ServletSecurity.TransportGuarantee#NONE} and no roles declared.
   *
   * @return {@link HttpConstraintElement}
   */
  public static HttpConstraintElement permitAll() {
    return new HttpConstraintElement(PERMIT);
  }

  /**
   * Assumes {@link jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic#PERMIT} and {@link
   * jakarta.servlet.annotation.ServletSecurity.TransportGuarantee#CONFIDENTIAL} and no roles
   * declared.
   *
   * @return {@link HttpConstraintElement}
   */
  public static HttpConstraintElement permitAllConfidential() {
    return new HttpConstraintElement(PERMIT, CONFIDENTIAL);
  }

  /**
   * Assumes {@link jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic#DENY} and {@link
   * jakarta.servlet.annotation.ServletSecurity.TransportGuarantee#NONE} and no roles declared.
   *
   * @return {@link HttpConstraintElement}
   */
  public static HttpConstraintElement denyAll() {
    return new HttpConstraintElement(DENY);
  }

  /**
   * Assumes {@link jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic#DENY} and {@link
   * jakarta.servlet.annotation.ServletSecurity.TransportGuarantee#CONFIDENTIAL} and no roles
   * declared.
   *
   * @return {@link HttpConstraintElement}
   */
  public static HttpConstraintElement denyAllConfidential() {
    return new HttpConstraintElement(DENY, CONFIDENTIAL);
  }

  /**
   * @param constraint One of {@link HttpConstraints#permit(String...)}, {@link
   *     HttpConstraints#permitAll()}, {@link HttpConstraints#permitConfidential(String...)},{@link
   *     HttpConstraints#permitAllConfidential()},{@link HttpConstraints#denyAll()},{@link
   *     HttpConstraints#denyAllConfidential()}
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpGet(HttpConstraintElement constraint) {
    return method("GET", constraint);
  }

  /**
   * Assumes {@link HttpConstraints#permitAll()}.
   *
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpGet() {
    return httpGet(null);
  }

  /**
   * @param constraint One of {@link HttpConstraints#permit(String...)}, {@link
   *     HttpConstraints#permitAll()}, {@link HttpConstraints#permitConfidential(String...)},{@link
   *     HttpConstraints#permitAllConfidential()},{@link HttpConstraints#denyAll()},{@link
   *     HttpConstraints#denyAllConfidential()}
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpPost(HttpConstraintElement constraint) {
    return method("POST", constraint);
  }

  /**
   * Assumes {@link HttpConstraints#permitAll()}.
   *
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpPost() {
    return httpPost(null);
  }

  /**
   * @param constraint One of {@link HttpConstraints#permit(String...)}, {@link
   *     HttpConstraints#permitAll()}, {@link HttpConstraints#permitConfidential(String...)},{@link
   *     HttpConstraints#permitAllConfidential()},{@link HttpConstraints#denyAll()},{@link
   *     HttpConstraints#denyAllConfidential()}
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpDelete(HttpConstraintElement constraint) {
    return method("DELETE", constraint);
  }

  /**
   * Assumes {@link HttpConstraints#permitAll()}.
   *
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpDelete() {
    return httpDelete(null);
  }

  /**
   * @param constraint One of {@link HttpConstraints#permit(String...)}, {@link
   *     HttpConstraints#permitAll()}, {@link HttpConstraints#permitConfidential(String...)},{@link
   *     HttpConstraints#permitAllConfidential()},{@link HttpConstraints#denyAll()},{@link
   *     HttpConstraints#denyAllConfidential()}
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpPut(HttpConstraintElement constraint) {
    return method("PUT", constraint);
  }

  /**
   * Assumes {@link HttpConstraints#permitAll()}.
   *
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpPut() {
    return httpPut(null);
  }

  /**
   * @param constraint One of {@link HttpConstraints#permit(String...)}, {@link
   *     HttpConstraints#permitAll()}, {@link HttpConstraints#permitConfidential(String...)},{@link
   *     HttpConstraints#permitAllConfidential()},{@link HttpConstraints#denyAll()},{@link
   *     HttpConstraints#denyAllConfidential()}
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpHead(HttpConstraintElement constraint) {
    return method("HEAD", constraint);
  }

  /**
   * Assumes {@link HttpConstraints#permitAll()}.
   *
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpHead() {
    return httpHead(null);
  }

  /**
   * @param constraint One of {@link HttpConstraints#permit(String...)}, {@link
   *     HttpConstraints#permitAll()}, {@link HttpConstraints#permitConfidential(String...)},{@link
   *     HttpConstraints#permitAllConfidential()},{@link HttpConstraints#denyAll()},{@link
   *     HttpConstraints#denyAllConfidential()}
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpOptions(HttpConstraintElement constraint) {
    return method("OPTIONS", constraint);
  }

  /**
   * Assumes {@link HttpConstraints#permitAll()}.
   *
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpOptions() {
    return httpOptions(null);
  }

  /**
   * @param constraint One of {@link HttpConstraints#permit(String...)}, {@link
   *     HttpConstraints#permitAll()}, {@link HttpConstraints#permitConfidential(String...)},{@link
   *     HttpConstraints#permitAllConfidential()},{@link HttpConstraints#denyAll()},{@link
   *     HttpConstraints#denyAllConfidential()}
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpTrace(HttpConstraintElement constraint) {
    return method("TRACE", constraint);
  }

  /**
   * Assumes {@link HttpConstraints#permitAll()}.
   *
   * @return {@link HttpMethodConstraintElement}
   */
  public static HttpMethodConstraintElement httpTrace() {
    return httpTrace(null);
  }

  private static HttpMethodConstraintElement method(String name, HttpConstraintElement constraint) {
    return constraint != null
        ? new HttpMethodConstraintElement(name, constraint)
        : new HttpMethodConstraintElement(name);
  }
}
