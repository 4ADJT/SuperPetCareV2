package br.com.superpetcare.superpetcare.domain.cart;

import br.com.superpetcare.superpetcare.domain.cart.dao.RegisterCart;
import br.com.superpetcare.superpetcare.domain.pet.PetRepository;
import br.com.superpetcare.superpetcare.domain.services.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ComponentCart {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PetRepository petRepository;

    public CartEntity registerCart(RegisterCart registerCart) {

        var listServices = registerCart.serviceId()
                .stream()
                .map(serviceRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        var petEntity = petRepository.getReferenceById(registerCart.petId());

        return new CartEntity(listServices, petEntity);

    }

}
