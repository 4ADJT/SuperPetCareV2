package br.com.superpetcare.superpetcare.controller;

import br.com.superpetcare.superpetcare.domain.pet.PetRepository;
import br.com.superpetcare.superpetcare.domain.cart.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Cart of Services API")
public class CartController {

    @Autowired
    private ServiceCart serviceCart;

    @Autowired
    private PetRepository petRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra Carinho", description = "Método responsável exibir registar o carinho.")
    public ResponseEntity<DetailCart> registerCart(@RequestBody @Valid RegisterCart registerPurchase, UriComponentsBuilder componentsBuilder) {

        var listServices = serviceCart.convertServiceIdsToServiceEntity(registerPurchase);
        var detailServices = serviceCart.detail(listServices);

        var petEntity = petRepository.getReferenceById(registerPurchase.petId());

        var purchase = new CartEntity(listServices, petEntity);
        serviceCart.save(purchase);

        var uri = componentsBuilder.path("cart/{id}").buildAndExpand(purchase.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailCart(purchase, detailServices));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar carinho", description = "Método responsável exibir detalhes do carinho.")
    public ResponseEntity<DetailCart> detailCats(@PathVariable UUID id) {
        var cart = serviceCart.findDetailPurchase(id);
        return ResponseEntity.ok(cart);
    }

    @GetMapping
    @Operation(summary = "Consultar Carinhos", description = "Método responsável exibir todos os carinho.")
    public ResponseEntity<List<DetailCart>> listCats(@PageableDefault(size = 10) Pageable pageable) {
        List<DetailCart> purchases = serviceCart.listAllDetailPurchase();
        return ResponseEntity.ok(purchases);
    }


}
