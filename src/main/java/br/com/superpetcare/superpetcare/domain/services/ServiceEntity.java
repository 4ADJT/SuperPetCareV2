package br.com.superpetcare.superpetcare.domain.services;

import br.com.superpetcare.superpetcare.domain.services.dao.RegisterService;
import br.com.superpetcare.superpetcare.domain.services.dao.UpdateService;
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
@Entity(name = "services")
@Table(name = "services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private float price;

    public ServiceEntity(RegisterService registerService) {
        name = registerService.name();
        description = registerService.description();
        price = registerService.price();
    }

    public void UpdateData(UpdateService updateService) {
        if (updateService.name() != null)
            name = updateService.name();
        if (updateService.description() != null)
            description = updateService.description();
        price = updateService.price();
    }
}
