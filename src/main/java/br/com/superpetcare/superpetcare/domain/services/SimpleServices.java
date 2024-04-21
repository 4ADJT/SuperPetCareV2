package br.com.superpetcare.superpetcare.domain.services;

import java.util.UUID;

public record SimpleServices(
        UUID id,
        String name,
        float price
) {
    public SimpleServices(ServiceEntity serviceEntity) {
        this(
                serviceEntity.getId(),
                serviceEntity.getName(),
                serviceEntity.getPrice()
        );
    }
}
