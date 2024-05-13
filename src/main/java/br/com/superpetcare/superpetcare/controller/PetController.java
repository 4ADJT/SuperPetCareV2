package br.com.superpetcare.superpetcare.controller;

import br.com.superpetcare.superpetcare.domain.pet.ComponentPet;
import br.com.superpetcare.superpetcare.domain.pet.PetEntity;
import br.com.superpetcare.superpetcare.domain.pet.PetRepository;
import br.com.superpetcare.superpetcare.domain.pet.dao.RegisterPet;
import br.com.superpetcare.superpetcare.domain.pet.dao.UpdatePet;
import br.com.superpetcare.superpetcare.domain.pet.dto.DetailPet;
import br.com.superpetcare.superpetcare.domain.pet.dto.SimplePet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/pet")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Pet data API")
public class PetController {

    @Autowired
    PetRepository petRepository;

    @Autowired
    ComponentPet componentPet;

    @PostMapping
    @Transactional
    @Operation(summary = "Registrar Pet", description = "Método responsável por registar o pet.")
    public ResponseEntity<DetailPet> registerPet(@RequestBody @Valid RegisterPet registerPet, UriComponentsBuilder componentsBuilder) {
        PetEntity petEntity = componentPet.registerPet(registerPet);
        petRepository.save(petEntity);
        var uri = componentsBuilder.path("pet/{id}").buildAndExpand(petEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailPet(petEntity));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalher Pet", description = "Método responsável por detalhar dados do pet.")
    public ResponseEntity<DetailPet> datailPet(@PathVariable UUID id) {
        var optionalPetEntity = petRepository.findById(id);
        return optionalPetEntity.map(
                petEntity -> ResponseEntity.ok(new DetailPet(petEntity))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar Pets", description = "Método responsável por listar todos os pet.")
    public ResponseEntity<Page<SimplePet>> listPets(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var listPet = petRepository.findAll(pageable).map(SimplePet::new);
        return ResponseEntity.ok(listPet);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualiza Pet", description = "Método responsável por atualizar o pet.")
    public ResponseEntity<DetailPet> UpdatePet(@RequestBody @Valid UpdatePet updatePet, @PathVariable UUID id) {
        var pet = petRepository.getReferenceById(id);
        pet.updateData(updatePet);
        return ResponseEntity.ok(new DetailPet(pet));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir Pet", description = "Método responsável por excluir o pet.")
    public ResponseEntity DeletePet(@PathVariable UUID id) {
        petRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
