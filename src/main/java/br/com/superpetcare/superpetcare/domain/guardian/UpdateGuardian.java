package br.com.superpetcare.superpetcare.domain.guardian;

import br.com.superpetcare.superpetcare.domain.address.DetailAddress;
import br.com.superpetcare.superpetcare.domain.address.RegisterAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateGuardian (
        @Email
        String email,
        @Pattern(regexp = "^\\d{11}$")
        String phone,
        @Valid
        RegisterAddress address
){
}
