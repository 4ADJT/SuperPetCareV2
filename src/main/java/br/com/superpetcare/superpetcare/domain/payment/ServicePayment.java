package br.com.superpetcare.superpetcare.domain.payment;

import br.com.superpetcare.superpetcare.domain.cart.DetailCart;
import br.com.superpetcare.superpetcare.domain.cart.ServiceCart;
import br.com.superpetcare.superpetcare.domain.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServicePayment {

    @Autowired
    ComponentPayment componentPayment;

    @Autowired
    ServiceCart serviceCart;

    public double totalAmauntOfServices(DetailCart cart) {
        return componentPayment.totalAmauntOfServices(cart);
    }

    public PaymentEntity findDetailPayment(UUID paymentId) {
        return componentPayment.findDetailPayment(paymentId);
    }

    public DetailPayment detailCartPayment(UUID cartId) {
        var detailCart = serviceCart.findDetailCart(cartId);
        var totalAmaunt = detailCart.services().stream().mapToDouble(DetailService::price).sum();
        return new DetailPayment(totalAmaunt, detailCart);

    }

    public PaymentEntity registerPayment(UUID id) {
        var cartEntity = serviceCart.findCartEntity(id);
        var totalAmaunt = serviceCart.findDetailCart(id).services().stream().mapToDouble(DetailService::price).sum();
        return componentPayment.save(cartEntity, totalAmaunt);
    }

    public DetailPayment detailPayment(UUID id) {
        var paymentEntity = componentPayment.findDetailPayment(id);
        var detailCart = serviceCart.findDetailCart(paymentEntity.getCart().getId());
        return new DetailPayment(paymentEntity, detailCart);
    }

    public PaymentEntity findPaymentEntity(UUID id) {
       return componentPayment.findPaymentEntity(id);
    }

    public List<PaymentEntity> all() {
        return componentPayment.listPayments();

    }
}
