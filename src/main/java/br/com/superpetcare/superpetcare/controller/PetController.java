package br.com.superpetcare.superpetcare.controller;

import br.com.superpetcare.superpetcare.domain.guardian.GuardianRepository;
import br.com.superpetcare.superpetcare.domain.pet.*;
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
    GuardianRepository guardianRepository;

    @Autowired
    PetRepository petRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registar Pet", description = "Método responsável registar o pet.")
    public ResponseEntity<DetailPet> registerPet(@RequestBody @Valid RegisterPet registerPet, UriComponentsBuilder componentsBuilder) {
        var guardian = guardianRepository.getReferenceById(registerPet.guardianId());
        var pet = new PetEntity(registerPet, guardian);
        petRepository.save(pet);
        var uri = componentsBuilder.path("pet/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailPet(pet));

    }

    @GetMapping
    @Operation(summary = "Listar Pets", description = "Método responsável listar todos os pet.")
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
    @Operation(summary = "Exluir Pet", description = "Método responsável por excluir o pet.")
    public ResponseEntity DeletePet(@PathVariable UUID id) {
        petRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
