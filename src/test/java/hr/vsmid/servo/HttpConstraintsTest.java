package hr.vsmid.servo;

import static jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic.DENY;
import static jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic.PERMIT;
import static jakarta.servlet.annotation.ServletSecurity.TransportGuarantee.CONFIDENTIAL;
import static jakarta.servlet.annotation.ServletSecurity.TransportGuarantee.NONE;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.servlet.HttpConstraintElement;
import jakarta.servlet.HttpMethodConstraintElement;
import org.junit.jupiter.api.Test;

class HttpConstraintsTest {

  @Test
  void permit() {
    HttpConstraintElement constraint = HttpConstraints.permit("admin");

    assertArrayEquals(new String[] {"admin"}, constraint.getRolesAllowed());
    assertEquals(PERMIT, constraint.getEmptyRoleSemantic());
    assertEquals(NONE, constraint.getTransportGuarantee());
  }

  @Test
  void permitConfidential() {
    HttpConstraintElement constraint = HttpConstraints.permitConfidential("admin");

    assertArrayEquals(new String[] {"admin"}, constraint.getRolesAllowed());
    assertEquals(PERMIT, constraint.getEmptyRoleSemantic());
    assertEquals(CONFIDENTIAL, constraint.getTransportGuarantee());
  }

  @Test
  void permitAll() {
    HttpConstraintElement constraint = HttpConstraints.permitAll();

    assertArrayEquals(new String[] {}, constraint.getRolesAllowed());
    assertEquals(PERMIT, constraint.getEmptyRoleSemantic());
    assertEquals(NONE, constraint.getTransportGuarantee());
  }

  @Test
  void permitAllConfidential() {
    HttpConstraintElement constraint = HttpConstraints.permitAllConfidential();

    assertArrayEquals(new String[] {}, constraint.getRolesAllowed());
    assertEquals(PERMIT, constraint.getEmptyRoleSemantic());
    assertEquals(CONFIDENTIAL, constraint.getTransportGuarantee());
  }

  @Test
  void denyAll() {
    HttpConstraintElement constraint = HttpConstraints.denyAll();

    assertArrayEquals(new String[] {}, constraint.getRolesAllowed());
    assertEquals(DENY, constraint.getEmptyRoleSemantic());
    assertEquals(NONE, constraint.getTransportGuarantee());
  }

  @Test
  void denyAllConfidential() {
    HttpConstraintElement constraint = HttpConstraints.denyAllConfidential();

    assertArrayEquals(new String[] {}, constraint.getRolesAllowed());
    assertEquals(DENY, constraint.getEmptyRoleSemantic());
    assertEquals(CONFIDENTIAL, constraint.getTransportGuarantee());
  }

  @Test
  void httpGet() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpGet();

    assertEquals("GET", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpGetWithConstraint() {
    HttpMethodConstraintElement methodConstraint =
        HttpConstraints.httpGet(HttpConstraints.permit("admin"));

    assertEquals("GET", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {"admin"}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpGetWithNullConstraint() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpGet(null);

    assertEquals("GET", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpPost() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpPost();

    assertEquals("POST", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpPostWithConstraint() {
    HttpMethodConstraintElement methodConstraint =
        HttpConstraints.httpPost(HttpConstraints.permit("admin"));

    assertEquals("POST", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {"admin"}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpPostWithNullConstraint() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpPost();

    assertEquals("POST", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpDelete() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpDelete();

    assertEquals("DELETE", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpDeleteWithConstraint() {
    HttpMethodConstraintElement methodConstraint =
        HttpConstraints.httpDelete(HttpConstraints.permit("admin"));

    assertEquals("DELETE", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {"admin"}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpDeleteWithNullConstraint() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpDelete(null);

    assertEquals("DELETE", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpPut() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpPut();

    assertEquals("PUT", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpPutWithConstraint() {
    HttpMethodConstraintElement methodConstraint =
        HttpConstraints.httpPut(HttpConstraints.permit("admin"));

    assertEquals("PUT", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {"admin"}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpPutWithNullConstraint() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpPut();

    assertEquals("PUT", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpHead() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpHead();

    assertEquals("HEAD", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpHeadWithConstraint() {
    HttpMethodConstraintElement methodConstraint =
        HttpConstraints.httpHead(HttpConstraints.permit("admin"));

    assertEquals("HEAD", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {"admin"}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpHeadWithNullConstraint() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpHead();

    assertEquals("HEAD", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpOptions() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpOptions();

    assertEquals("OPTIONS", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpOptionsWithConstraint() {
    HttpMethodConstraintElement methodConstraint =
        HttpConstraints.httpOptions(HttpConstraints.permit("admin"));

    assertEquals("OPTIONS", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {"admin"}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpOptionsWithNullConstraint() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpOptions();

    assertEquals("OPTIONS", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpTrace() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpTrace();

    assertEquals("TRACE", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpTraceWithConstraint() {
    HttpMethodConstraintElement methodConstraint =
        HttpConstraints.httpTrace(HttpConstraints.permit("admin"));

    assertEquals("TRACE", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {"admin"}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }

  @Test
  void httpTraceWithNullConstraint() {
    HttpMethodConstraintElement methodConstraint = HttpConstraints.httpTrace(null);

    assertEquals("TRACE", methodConstraint.getMethodName());
    assertArrayEquals(new String[] {}, methodConstraint.getRolesAllowed());
    assertEquals(PERMIT, methodConstraint.getEmptyRoleSemantic());
    assertEquals(NONE, methodConstraint.getTransportGuarantee());
  }
}
