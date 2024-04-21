package br.com.superpetcare.superpetcare.domain.services;

import jakarta.validation.constraints.NotNull;

public record UpdateService(
        String name,
        String description,
        @NotNull
        float price
) {
}
