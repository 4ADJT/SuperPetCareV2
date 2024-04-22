package br.com.superpetcare.superpetcare.domain.cart;

import br.com.superpetcare.superpetcare.domain.pet.SimplePet;
import br.com.superpetcare.superpetcare.domain.services.SimpleServices;

import java.util.List;
import java.util.UUID;

public record SimpleCart(
        UUID id,
        List<SimpleServices> services,
        SimplePet pet
) {
    public SimpleCart(CartEntity cartEntity) {
        this(
                cartEntity.getId(),
                cartEntity.getServices().stream().map(SimpleServices::new).toList(),
                new SimplePet(cartEntity.getPet())
        );
    }
}
