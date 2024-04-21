package br.com.superpetcare.superpetcare.domain.payment;

import jakarta.validation.constraints.NotNull;

public record UpdatePayment(
        @NotNull
        PaymentStatus paymentStatus
) {
}
