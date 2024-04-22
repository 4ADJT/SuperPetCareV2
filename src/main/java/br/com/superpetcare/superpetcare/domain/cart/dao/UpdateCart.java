package br.com.superpetcare.superpetcare.domain.cart.dao;

import br.com.superpetcare.superpetcare.domain.services.ServiceEntity;

import java.util.List;

public record UpdateCart(
        List<ServiceEntity> services
) {
}
