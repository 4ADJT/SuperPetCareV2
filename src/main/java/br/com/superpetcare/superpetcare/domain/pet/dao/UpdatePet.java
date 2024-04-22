package br.com.superpetcare.superpetcare.domain.pet.dao;

import br.com.superpetcare.superpetcare.domain.pet.PetBehavior;
import br.com.superpetcare.superpetcare.domain.pet.PetCategory;
import br.com.superpetcare.superpetcare.domain.pet.PetGender;
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
