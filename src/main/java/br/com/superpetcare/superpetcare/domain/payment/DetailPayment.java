package br.com.superpetcare.superpetcare.domain.payment;

import br.com.superpetcare.superpetcare.domain.cart.CartEntity;
import br.com.superpetcare.superpetcare.domain.cart.DetailCart;
import br.com.superpetcare.superpetcare.domain.services.ServiceEntity;

import java.util.Date;
import java.util.UUID;

public record DetailPayment(
        UUID id,
        DetailCart cart,
        Double totalAmount,
        PaymentStatus paymentStatus,
        Date paymentDate
) {

    public DetailPayment(CartEntity cartEntity) {
        this(
                null,
                new DetailCart(cartEntity),
                cartEntity.getServices().stream().mapToDouble(ServiceEntity::getPrice).sum(),
                PaymentStatus.PENDING,
                null
        );
    }

    public DetailPayment(PaymentEntity payment) {
        this(
                payment.getId(),
                new DetailCart(payment.getCart()),
                payment.getTotalAmount(),
                payment.getPaymentStatus(),
                payment.getPaymentDate()
        );
    }
}
