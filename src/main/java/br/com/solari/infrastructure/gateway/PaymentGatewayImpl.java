package br.com.solari.infrastructure.gateway;

import br.com.solari.application.domain.Payment;
import br.com.solari.application.gateway.PaymentGateway;
import br.com.solari.infrastructure.persistence.entity.PaymentEntity;
import br.com.solari.infrastructure.persistence.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

  private final PaymentRepository paymentRepository;

  @Override
  public Payment save(Payment payment) {
    var entity =
        PaymentEntity.builder()
            .token(payment.getToken())
            .status(payment.getStatus())
            .method(payment.getMethod())
            .build();

    var saved = paymentRepository.save(entity);

    return toDomain(saved);
  }

  private Payment toDomain(PaymentEntity entity) {
    return Payment.builder()
        .id(entity.getId())
        .token(entity.getToken())
        .status(entity.getStatus())
        .method(entity.getMethod())
        .build();
  }
}
