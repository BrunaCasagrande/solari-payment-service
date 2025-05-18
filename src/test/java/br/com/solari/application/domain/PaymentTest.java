package br.com.solari.application.domain;

import static org.junit.jupiter.api.Assertions.*;

import br.com.solari.application.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

class PaymentTest {

  @Test
  void shouldCreatePaymentSuccessfully() {
    Payment payment =
        Payment.createPayment("validToken", PaymentStatus.PENDING, PaymentMethod.CREDIT_CARD);

    assertNotNull(payment);
    assertEquals("validToken", payment.getToken());
    assertEquals(PaymentStatus.PENDING, payment.getStatus());
    assertEquals(PaymentMethod.CREDIT_CARD, payment.getMethod());
  }

  @Test
  void shouldThrowExceptionWhenTokenIsNull() {
    DomainException exception =
        assertThrows(
            DomainException.class,
            () -> Payment.createPayment(null, PaymentStatus.PENDING, PaymentMethod.CREDIT_CARD));

    assertTrue(exception.getMessage().contains("Token is required"));
  }

  @Test
  void shouldThrowExceptionWhenStatusIsNull() {
    DomainException exception =
        assertThrows(
            DomainException.class,
            () -> Payment.createPayment("validToken", null, PaymentMethod.CREDIT_CARD));

    assertTrue(exception.getMessage().contains("Payment status is required"));
  }

  @Test
  void shouldThrowExceptionWhenMethodIsNull() {
    DomainException exception =
        assertThrows(
            DomainException.class,
            () -> Payment.createPayment("validToken", PaymentStatus.PENDING, null));

    assertTrue(exception.getMessage().contains("Payment method is required"));
  }
}
