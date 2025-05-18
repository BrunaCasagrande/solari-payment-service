package br.com.solari.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.solari.application.domain.Payment;
import br.com.solari.application.domain.PaymentMethod;
import br.com.solari.application.domain.PaymentStatus;
import br.com.solari.application.dto.PaymentRequestDto;
import br.com.solari.application.usecase.CreatePayment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private CreatePayment createPayment;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreatePaymentSuccessfully() throws Exception {
    PaymentRequestDto requestDto = new PaymentRequestDto("validToken");
    Payment createdPayment =
        Payment.builder()
            .id(1)
            .token("validToken")
            .status(PaymentStatus.APPROVED)
            .method(PaymentMethod.CREDIT_CARD)
            .build();

    when(createPayment.execute(any(PaymentRequestDto.class))).thenReturn(createdPayment);

    mockMvc
        .perform(
            post("/solari/v1/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/solari/v1/payments/1"))
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.token").value("validToken"))
        .andExpect(jsonPath("$.status").value("APPROVED"))
        .andExpect(jsonPath("$.method").value("CREDIT_CARD"));
  }
}
