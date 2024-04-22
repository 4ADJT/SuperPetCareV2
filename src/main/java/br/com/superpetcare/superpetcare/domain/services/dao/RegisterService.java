package br.com.superpetcare.superpetcare.domain.services.dao;

import jakarta.validation.constraints.NotNull;

public record RegisterService(
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        float price
) {
}
