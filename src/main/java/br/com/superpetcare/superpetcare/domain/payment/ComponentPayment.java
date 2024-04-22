package br.com.superpetcare.superpetcare.domain.payment;

import br.com.superpetcare.superpetcare.domain.cart.*;
import br.com.superpetcare.superpetcare.domain.services.DetailService;
import br.com.superpetcare.superpetcare.domain.services.ServiceEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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
