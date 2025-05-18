package br.com.solari.infrastructure.controller;

import br.com.solari.application.domain.Payment;
import br.com.solari.application.dto.PaymentRequestDto;
import br.com.solari.application.usecase.CreatePayment;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solari/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

  private final CreatePayment createPayment;

  @PostMapping
  public ResponseEntity<Payment> create(@Valid @RequestBody PaymentRequestDto request) {
    final var created = createPayment.execute(request);

    URI location = URI.create(String.format("/solari/v1/payments/%d", created.getId()));
    return ResponseEntity.created(location).body(created);
  }
}
