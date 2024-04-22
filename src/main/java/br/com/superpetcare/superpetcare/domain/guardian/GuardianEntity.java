package br.com.superpetcare.superpetcare.domain.guardian;

import br.com.superpetcare.superpetcare.domain.address.AddressEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "guardian")
@Table(name = "guardian")
public class GuardianEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public GuardianEntity(RegisterGuardian registerGuardian) {
        firstName = registerGuardian.firstName();
        lastName = registerGuardian.lastName();
        email = registerGuardian.email();
        phone = registerGuardian.phone();
        address = new AddressEntity(registerGuardian.address());
    }

    public GuardianEntity(GuardianEntity guardian) {
        id = guardian.id;
        firstName = guardian.firstName;
        lastName = guardian.lastName;
        email = guardian.email;
        phone = guardian.phone;
    }

    public void updateData(UpdateGuardian updateGuardian) {
        if (updateGuardian.email() != null)
            this.email = updateGuardian.email();
        if (updateGuardian.phone() != null)
            this.phone = updateGuardian.phone();
        if (updateGuardian.address() != null)
            this.address.updateData(updateGuardian.address());
    }
}
