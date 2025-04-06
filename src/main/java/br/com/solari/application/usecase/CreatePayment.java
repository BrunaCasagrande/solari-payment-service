package br.com.solari.application.usecase;

import br.com.solari.application.domain.Payment;
import br.com.solari.application.dto.PaymentRequestDto;
import br.com.solari.application.gateway.PaymentGateway;
import br.com.solari.application.gateway.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePayment {

  private final PaymentGateway paymentGateway;
  private final PaymentProcessor paymentProcessor;

  public Payment execute(final PaymentRequestDto request) {

    final Payment processedPayment = paymentProcessor.processPayment(request.getToken());

    return paymentGateway.save(processedPayment);
  }
}
