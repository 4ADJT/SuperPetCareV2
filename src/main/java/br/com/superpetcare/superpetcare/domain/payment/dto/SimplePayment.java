package br.com.superpetcare.superpetcare.domain.payment.dto;

import br.com.superpetcare.superpetcare.domain.cart.dto.SimpleCart;
import br.com.superpetcare.superpetcare.domain.payment.PaymentEntity;
import br.com.superpetcare.superpetcare.domain.payment.PaymentStatus;

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
