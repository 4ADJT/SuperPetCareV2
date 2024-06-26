package br.com.superpetcare.superpetcare.domain.services.dto;

import br.com.superpetcare.superpetcare.domain.services.ServiceEntity;

import java.util.UUID;

public record DetailService(
        UUID id,
        String name,
        String description,
        float price
) {

    public DetailService(ServiceEntity service) {
        this(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice()
        );
    }
}


