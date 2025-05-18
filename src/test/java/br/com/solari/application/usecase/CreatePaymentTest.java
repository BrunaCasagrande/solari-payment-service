package br.com.solari.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import br.com.solari.application.domain.Payment;
import br.com.solari.application.dto.PaymentRequestDto;
import br.com.solari.application.gateway.PaymentGateway;
import br.com.solari.application.gateway.PaymentProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CreatePaymentTest {

  @Mock private PaymentGateway paymentGateway;

  @Mock private PaymentProcessor paymentProcessor;

  @InjectMocks private CreatePayment createPayment;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreatePaymentSuccessfully() {
    PaymentRequestDto requestDto = new PaymentRequestDto("validToken");
    Payment processedPayment = new Payment(1, "validToken", null, null);
    Payment savedPayment = new Payment(1, "validToken", null, null);

    when(paymentProcessor.processPayment(requestDto.getToken())).thenReturn(processedPayment);
    when(paymentGateway.save(processedPayment)).thenReturn(savedPayment);

    Payment result = createPayment.execute(requestDto);

    assertEquals(savedPayment, result);
    verify(paymentProcessor, times(1)).processPayment(requestDto.getToken());
    verify(paymentGateway, times(1)).save(processedPayment);
  }
}
