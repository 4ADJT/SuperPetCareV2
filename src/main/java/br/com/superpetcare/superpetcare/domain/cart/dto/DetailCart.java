package br.com.superpetcare.superpetcare.domain.cart.dto;

import br.com.superpetcare.superpetcare.domain.cart.CartEntity;
import br.com.superpetcare.superpetcare.domain.pet.dto.DetailPet;
import br.com.superpetcare.superpetcare.domain.services.dto.DetailService;

import java.util.List;
import java.util.UUID;

public record DetailCart(
        UUID id,
        DetailPet pet,
        List<DetailService> services
) {

    public DetailCart(CartEntity cart) {
        this(
                cart.getId(),
                new DetailPet(cart.getPet()),
                cart.getServices().stream().map(DetailService::new).toList()
        );
    }
}
