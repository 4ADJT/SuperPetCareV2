package br.com.superpetcare.superpetcare.domain.cart;

import br.com.superpetcare.superpetcare.domain.pet.DetailPet;
import br.com.superpetcare.superpetcare.domain.services.DetailService;

import java.util.List;
import java.util.UUID;

public record DetailCart(
        UUID id,
        DetailPet pet,
        List<DetailService> services
) {
    public DetailCart(CartEntity purchase, List<DetailService> detailServices) {
        this(
                purchase.getId(),
                new DetailPet(purchase.getPet()),
                detailServices
        );
    }
}
