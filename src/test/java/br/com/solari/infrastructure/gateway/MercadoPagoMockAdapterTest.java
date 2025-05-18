package br.com.solari.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.*;

import br.com.solari.application.domain.Payment;
import br.com.solari.application.domain.PaymentMethod;
import br.com.solari.application.domain.PaymentStatus;
import org.junit.jupiter.api.Test;

class MercadoPagoMockAdapterTest {

  @Test
  void shouldProcessPaymentSuccessfully() {
    MercadoPagoMockAdapter adapter = new MercadoPagoMockAdapter();
    String token = "validToken";

    Payment payment = adapter.processPayment(token);

    assertNotNull(payment);
    assertEquals(token, payment.getToken());
    assertEquals(PaymentStatus.APPROVED, payment.getStatus());
    assertEquals(PaymentMethod.CREDIT_CARD, payment.getMethod());
  }
}
