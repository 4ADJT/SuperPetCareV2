package br.com.superpetcare.superpetcare.domain.pet.dao;

import br.com.superpetcare.superpetcare.domain.pet.PetBehavior;
import br.com.superpetcare.superpetcare.domain.pet.PetCategory;
import br.com.superpetcare.superpetcare.domain.pet.PetGender;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RegisterPet(
        @NotNull
        UUID guardianId,
        @NotNull
        String name,
        @NotNull
        PetCategory category,
        @NotNull
        String breed,
        @NotNull
        int age,
        @NotNull
        PetGender gender,
        @NotNull
        int weight,
        @NotNull
        PetBehavior behavior
) {
}
