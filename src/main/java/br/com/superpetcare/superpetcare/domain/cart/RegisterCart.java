package br.com.superpetcare.superpetcare.domain.cart;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record RegisterCart(

        @NotNull
        List<UUID> serviceId,
        @NotNull
        UUID petId

) {
}
