package br.com.solari.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.com.solari.application.domain.Payment;
import br.com.solari.application.domain.PaymentMethod;
import br.com.solari.application.domain.PaymentStatus;
import br.com.solari.infrastructure.persistence.entity.PaymentEntity;
import br.com.solari.infrastructure.persistence.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PaymentGatewayImplTest {

  @Mock private PaymentRepository paymentRepository;

  @InjectMocks private PaymentGatewayImpl paymentGateway;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldSavePaymentSuccessfully() {
    Payment payment =
        Payment.builder()
            .token("validToken")
            .status(PaymentStatus.APPROVED)
            .method(PaymentMethod.CREDIT_CARD)
            .build();

    PaymentEntity savedEntity =
        PaymentEntity.builder()
            .id(1)
            .token("validToken")
            .status(PaymentStatus.APPROVED)
            .method(PaymentMethod.CREDIT_CARD)
            .build();

    when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(savedEntity);

    Payment result = paymentGateway.save(payment);

    assertEquals(1, result.getId());
    assertEquals("validToken", result.getToken());
    assertEquals(PaymentStatus.APPROVED, result.getStatus());
    assertEquals(PaymentMethod.CREDIT_CARD, result.getMethod());
    verify(paymentRepository, times(1)).save(any(PaymentEntity.class));
  }
}
