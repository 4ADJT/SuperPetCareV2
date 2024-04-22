package br.com.superpetcare.superpetcare.domain.guardian.dto;

import br.com.superpetcare.superpetcare.domain.address.dto.DetailAddress;
import br.com.superpetcare.superpetcare.domain.guardian.GuardianEntity;
import br.com.superpetcare.superpetcare.domain.pet.dto.DetailPet;

import java.util.List;
import java.util.UUID;

public record DetailGuardian(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        DetailAddress address,
        List<DetailPet> pets
) {
    public DetailGuardian(GuardianEntity guardian) {
        this(
                guardian.getId(),
                guardian.getFirstName(),
                guardian.getLastName(),
                guardian.getEmail(),
                guardian.getPhone(),
                new DetailAddress(guardian.getAddress()),
                guardian.getPets().stream().map(DetailPet::new).toList()
        );
    }
}
