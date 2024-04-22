package br.com.superpetcare.superpetcare.domain.services.dto;

import br.com.superpetcare.superpetcare.domain.services.ServiceEntity;

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
