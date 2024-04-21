package br.com.superpetcare.superpetcare.domain.payment;

import br.com.superpetcare.superpetcare.domain.cart.DetailCart;

import java.util.Date;
import java.util.UUID;

public record DetailPayment(
        UUID id,
        DetailCart cart,
        Double totalAmount,
        PaymentStatus paymentStatus,
        Date paymentDate
) {

    public DetailPayment(double totalAmaunt, DetailCart detailCart) {
        this(
                null,
                detailCart,
                totalAmaunt,
                PaymentStatus.PENDING,
                null
        );
    }

    public DetailPayment(PaymentEntity paymentEntity, DetailCart detailCart) {
        this(
                paymentEntity.getId(),
                detailCart,
                paymentEntity.getTotalAmount(),
                paymentEntity.getPaymentStatus(),
                paymentEntity.getPaymentDate()
        );
    }
}
