package br.com.superpetcare.superpetcare.domain.cart;

import br.com.superpetcare.superpetcare.domain.services.DetailService;
import br.com.superpetcare.superpetcare.domain.services.ServiceEntity;
import br.com.superpetcare.superpetcare.domain.services.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Component
public class ComponentCart {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CartRepository cartRepository;

    public List<ServiceEntity> convertServiceIdsToServiceEntity(RegisterCart registerPurchase) {
        List<UUID> serviceIds = registerPurchase.serviceId();
        return serviceIds.stream()
                .map(serviceRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public List<DetailService> detailService(List<ServiceEntity> serviceEntities) {
        return serviceEntities.stream().map(DetailService::new).toList();
    }

    public List<DetailCart> listAllDetailCart() {
        return cartRepository.findAll()
                .stream().map(purchaseEntity -> {
                    List<DetailService> ds = purchaseEntity.getServices().stream().map(DetailService::new).toList();
                    return new DetailCart(purchaseEntity, ds);
                }).toList();
    }

    public DetailCart findDetailCart(UUID id) {
        return cartRepository.findById(id)
                .map(purchaseEntity -> {
                    List<DetailService> ds = purchaseEntity.getServices().stream().map(DetailService::new).toList();
                    return new DetailCart(purchaseEntity, ds);
                }).orElseThrow(() -> new EntityNotFoundException("No value present"));

    }

    public CartEntity findCartEntity(UUID id) {
        return cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No value present"));
    }
}
