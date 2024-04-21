package br.com.superpetcare.superpetcare.domain.pet;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdatePet(
        String name,
        PetCategory category,
        String breed,
        @NotNull
        int age,
        PetGender gender,
        @NotNull
        int weight,
        PetBehavior behavior
) {
}
