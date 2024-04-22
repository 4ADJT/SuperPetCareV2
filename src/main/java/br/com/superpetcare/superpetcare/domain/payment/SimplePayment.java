package br.com.superpetcare.superpetcare.domain.payment;

import br.com.superpetcare.superpetcare.domain.cart.SimpleCart;

import java.util.Date;
import java.util.UUID;

public record SimplePayment(
        UUID id,
        SimpleCart cart,
        double totalAmount,
        PaymentStatus paymentStatus,
        Date paymentDate
) {


    public SimplePayment(PaymentEntity paymentEntity) {
        this(
                paymentEntity.getId(),
                new SimpleCart(paymentEntity.getCart()),
                paymentEntity.getTotalAmount(),
                paymentEntity.getPaymentStatus(),
                paymentEntity.getPaymentDate()
        );
    }
}
