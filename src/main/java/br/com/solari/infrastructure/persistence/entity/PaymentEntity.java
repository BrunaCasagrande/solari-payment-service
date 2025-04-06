package br.com.solari.infrastructure.persistence.entity;

import br.com.solari.application.domain.PaymentMethod;
import br.com.solari.application.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "solari_payment_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "token", nullable = false)
  private String token;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private PaymentStatus status;

  @Enumerated(EnumType.STRING)
  @Column(name = "method", nullable = false)
  private PaymentMethod method;
}
