package br.com.superpetcare.superpetcare.domain.pet;

import jakarta.validation.constraints.NotNull;

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
