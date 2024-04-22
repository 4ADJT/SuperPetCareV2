package br.com.superpetcare.superpetcare.domain.cart;

import br.com.superpetcare.superpetcare.domain.pet.PetEntity;
import br.com.superpetcare.superpetcare.domain.services.ServiceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "cart")
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany
    @JoinTable(
            name = "cart_service",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<ServiceEntity> services;

    @ManyToOne
    private PetEntity pet;

    public CartEntity(List<ServiceEntity> listServices, PetEntity petEntity) {
        this.pet = petEntity;
        this.services = listServices;
    }

}
