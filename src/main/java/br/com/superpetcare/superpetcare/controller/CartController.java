package br.com.superpetcare.superpetcare.controller;

import br.com.superpetcare.superpetcare.domain.cart.*;
import br.com.superpetcare.superpetcare.domain.pet.PetRepository;
import br.com.superpetcare.superpetcare.domain.services.DetailService;
import br.com.superpetcare.superpetcare.domain.services.ServiceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Cart of Services API")
public class CartController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ComponentCart componentCart;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra Carinho", description = "Método responsável exibir registar o carinho.")
    public ResponseEntity<DetailCart> registerCart(@RequestBody @Valid RegisterCart registerCart, UriComponentsBuilder componentsBuilder) {

        CartEntity cartEntity = componentCart.registerCart(registerCart);
        cartRepository.save(cartEntity);

        var uri = componentsBuilder.path("cart/{id}").buildAndExpand(cartEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailCart(cartEntity));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhar carinho", description = "Método responsável exibir detalhes do carinho.")
    public ResponseEntity<DetailCart> detailCats(@PathVariable UUID id) {
        var cart = cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No value present"));
        return ResponseEntity.ok(new DetailCart(cart));
    }

    @GetMapping
    @Operation(summary = "Consultar Carinhos", description = "Método responsável exibir todos os carinho.")
    public ResponseEntity<Page<DetailCart>> listCats(@PageableDefault(size = 10) Pageable pageable) {
        var page = cartRepository.findAll(pageable).map(DetailCart::new);
        return ResponseEntity.ok(page);
    }


}
