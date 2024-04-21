package br.com.superpetcare.superpetcare.domain.pet;

import br.com.superpetcare.superpetcare.domain.guardian.DetailGuardian;
import br.com.superpetcare.superpetcare.domain.guardian.GuardianEntity;
import jakarta.persistence.*;

import java.util.UUID;

public record DetailPet(
        UUID id,
        String name,
        PetCategory category,
        String breed,
        int age,
        PetGender gender,
        int weight,
        PetBehavior behavior,
        DetailGuardian guardian
) {
    public DetailPet(PetEntity pet) {
        this(
                pet.getId(),
                pet.getName(),
                pet.getCategory(),
                pet.getBreed(),
                pet.getAge(),
                pet.getGender(),
                pet.getWeight(),
                pet.getBehavior(),
                new DetailGuardian(pet.getGuardian())
        );
    }
}
