package br.com.superpetcare.superpetcare.domain.payment.dao;

import br.com.superpetcare.superpetcare.domain.payment.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public record UpdatePayment(
        @NotNull
        PaymentStatus paymentStatus
) {
}
