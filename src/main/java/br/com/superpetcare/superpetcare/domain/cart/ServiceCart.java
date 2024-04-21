package br.com.superpetcare.superpetcare.domain.cart;

import br.com.superpetcare.superpetcare.domain.services.DetailService;
import br.com.superpetcare.superpetcare.domain.services.ServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceCart {
    @Autowired
    ComponentCart componentCart;

    @Autowired
    CartRepository purchaseRepository;

    public List<ServiceEntity> convertServiceIdsToServiceEntity(RegisterCart registerPurchase) {
        return componentCart.convertServiceIdsToServiceEntity(registerPurchase);
    }

    public List<DetailService> detail(List<ServiceEntity> registerPurchase) {
        return componentCart.detailService(registerPurchase);
    }

    public void save(CartEntity purchase) {
        purchaseRepository.save(purchase);
    }

    public List<DetailCart> listAllDetailPurchase() {
        return componentCart.listAllDetailCart();
    }

    public DetailCart findDetailPurchase(UUID id) {
        return componentCart.findDetailCart(id);
    }

    public DetailCart findDetailCart(UUID id) {
        return componentCart.findDetailCart(id);
    }

    public CartEntity findCartEntity(UUID id) {
        return componentCart.findCartEntity(id);
    }
}
