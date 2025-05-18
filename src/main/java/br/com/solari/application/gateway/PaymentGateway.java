package br.com.solari.application.gateway;

import br.com.solari.application.domain.Payment;

public interface PaymentGateway {

  Payment save(Payment payment);
}
