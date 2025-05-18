package br.com.solari.application.domain;

import br.com.solari.application.domain.exception.DomainException;
import br.com.solari.application.domain.exception.ErrorDetail;
import jakarta.validation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

  private Integer id;

  @NotBlank(message = "Token is required")
  private String token;

  @NotNull(message = "Payment status is required")
  private PaymentStatus status;

  @NotNull(message = "Payment method is required")
  private PaymentMethod method;

  public static Payment createPayment(
      final String token, final PaymentStatus status, final PaymentMethod method) {
    final var payment = Payment.builder().token(token).status(status).method(method).build();

    validate(payment);

    return payment;
  }

  private static void validate(final Payment payment) {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    final Set<ConstraintViolation<Payment>> violations = validator.validate(payment);

    if (!violations.isEmpty()) {
      final List<ErrorDetail> errors =
          violations.stream()
              .map(
                  v ->
                      new ErrorDetail(
                          v.getPropertyPath().toString(), v.getMessage(), v.getInvalidValue()))
              .toList();

      throw new DomainException(errors.get(0).errorMessage(), "domain_exception", errors);
    }
  }
}
