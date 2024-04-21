package br.com.superpetcare.superpetcare.domain.payment;

import br.com.superpetcare.superpetcare.domain.cart.CartEntity;
import br.com.superpetcare.superpetcare.domain.cart.DetailCart;
import br.com.superpetcare.superpetcare.domain.services.DetailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ComponentPayment {

    @Autowired
    private PaymentRepository paymentRepository;

    public double totalAmauntOfServices(DetailCart cart) {
        return cart.services().stream().mapToDouble(DetailService::price).sum();
    }

    public PaymentEntity findDetailPayment(UUID paymentId) {
        return paymentRepository.getReferenceById(paymentId);
    }

    public PaymentEntity save(CartEntity cartEntity, double totalAmaunt) {
        return paymentRepository.save(
                new PaymentEntity(cartEntity, totalAmaunt)
        );
    }

    public PaymentEntity findPaymentEntity(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No value present"));
    }

    public List<PaymentEntity> listPayments() {
        return paymentRepository.findAll();
    }
}
