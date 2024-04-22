package br.com.superpetcare.superpetcare.domain.pet.dto;

import br.com.superpetcare.superpetcare.domain.guardian.dto.DetailGuardian;
import br.com.superpetcare.superpetcare.domain.pet.PetBehavior;
import br.com.superpetcare.superpetcare.domain.pet.PetCategory;
import br.com.superpetcare.superpetcare.domain.pet.PetEntity;
import br.com.superpetcare.superpetcare.domain.pet.PetGender;

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
