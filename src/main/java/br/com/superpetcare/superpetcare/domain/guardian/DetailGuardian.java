package br.com.superpetcare.superpetcare.domain.guardian;

import br.com.superpetcare.superpetcare.domain.address.DetailAddress;

import java.util.UUID;

public record DetailGuardian(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        DetailAddress address
) {
    public DetailGuardian(GuardianEntity guardian) {
        this(
                guardian.getId(),
                guardian.getFirstName(),
                guardian.getLastName(),
                guardian.getEmail(),
                guardian.getPhone(),
                new DetailAddress(guardian.getAddress())
        );
    }
}
