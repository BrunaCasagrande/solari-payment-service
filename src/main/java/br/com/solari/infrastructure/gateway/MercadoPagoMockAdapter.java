package br.com.solari.infrastructure.gateway;

import br.com.solari.application.domain.Payment;
import br.com.solari.application.domain.PaymentMethod;
import br.com.solari.application.domain.PaymentStatus;
import br.com.solari.application.gateway.PaymentProcessor;
import org.springframework.stereotype.Component;

@Component
public class MercadoPagoMockAdapter implements PaymentProcessor {

    @Override
    public Payment processPayment(String token) {

        return Payment.createPayment(
                token,
                PaymentStatus.APPROVED,
                PaymentMethod.CREDIT_CARD
        );
    }
}
