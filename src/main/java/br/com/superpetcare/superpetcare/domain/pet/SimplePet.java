package br.com.superpetcare.superpetcare.domain.pet;

import br.com.superpetcare.superpetcare.domain.guardian.DetailGuardian;
import br.com.superpetcare.superpetcare.domain.guardian.SimpleGuardian;

import java.util.UUID;

public record SimplePet(
        UUID id,
        String name,
        String breed,
        int age,
        PetGender gender,
        int weight,
        SimpleGuardian guardian
) {
    public SimplePet(PetEntity petEntity) {
        this(
                petEntity.getId(),
                petEntity.getName(),
                petEntity.getBreed(),
                petEntity.getAge(),
                petEntity.getGender(),
                petEntity.getWeight(),
                new SimpleGuardian(petEntity.getGuardian())
        );
    }
}
