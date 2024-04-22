package br.com.superpetcare.superpetcare.components;

import br.com.superpetcare.superpetcare.domain.cart.CartRepository;
import br.com.superpetcare.superpetcare.services.ServiceCart;
import br.com.superpetcare.superpetcare.domain.payment.PaymentEntity;
import br.com.superpetcare.superpetcare.domain.payment.PaymentRepository;
import br.com.superpetcare.superpetcare.services.ServicePayment;
import br.com.superpetcare.superpetcare.domain.payment.dao.ResgiterPayment;
import br.com.superpetcare.superpetcare.domain.payment.dto.DetailPayment;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ComponentPayment {

    @Autowired
    ServiceCart serviceCart;

    @Autowired
    ServicePayment servicePayment;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    CartRepository cartRepository;

    public PaymentEntity registerPayment(ResgiterPayment resgiterPayment) {
        var optionalCartEntity = cartRepository.findById(resgiterPayment.cartId());
        return paymentRepository.save(
                new PaymentEntity(
                        optionalCartEntity.orElseThrow(() -> new EntityNotFoundException("Cart not found"))
                ));
    }

    public DetailPayment previewPayment(UUID cartId) {
        var optionalCartEntity = cartRepository.findById(cartId);
        return new DetailPayment(
                optionalCartEntity.orElseThrow(() -> new EntityNotFoundException("Cart not found"))
        );

    }

}
