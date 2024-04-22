package br.com.superpetcare.superpetcare.controller;

import br.com.superpetcare.superpetcare.domain.guardian.*;
import br.com.superpetcare.superpetcare.domain.guardian.dao.RegisterGuardian;
import br.com.superpetcare.superpetcare.domain.guardian.dao.UpdateGuardian;
import br.com.superpetcare.superpetcare.domain.guardian.dto.DetailGuardian;
import br.com.superpetcare.superpetcare.domain.guardian.dto.SimpleGuardian;
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
@RequestMapping("/guardian")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Guardian data API")
public class GuardianController {

    @Autowired
    GuardianRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra tutores", description = "Método responsável por registrar os tutores dos pets.")
    public ResponseEntity<DetailGuardian> registerGuardian(@RequestBody @Valid RegisterGuardian registerGuardian, UriComponentsBuilder componentsBuilder) {
        var guardian = new GuardianEntity(registerGuardian);
        repository.save(guardian);
        var uri = componentsBuilder.path("guardian/{id}").buildAndExpand(guardian.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailGuardian(guardian));
    }

    @GetMapping
    @Operation(summary = "Consultar tutores", description = "Método responsável por listar todos tutores de pets.")
    public ResponseEntity<Page<SimpleGuardian>> ListGuardian(@PageableDefault(size = 10, sort = {"firstName", "lastName"}) Pageable pageable) {
        var page = repository.findAll(pageable).map(SimpleGuardian::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar tutor", description = "Método responsável exibir detalhes do tutor.")
    public ResponseEntity<DetailGuardian> detailGuardian(@PathVariable UUID id) {
        var optionalGuardian = repository.findById(id);
        return optionalGuardian.map(guardian ->
                ResponseEntity.ok(new DetailGuardian(guardian))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualiza tutor", description = "Método responsável por atualizar o tutor.")
    public ResponseEntity<DetailGuardian> UpdateGuardian(@RequestBody @Valid UpdateGuardian updateGuardian, @PathVariable UUID id) {
        var guardian = repository.getReferenceById(id);
        guardian.updateData(updateGuardian);
        return ResponseEntity.ok(new DetailGuardian(guardian));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tutor", description = "Método responsável por excluir o tutor.")
    public ResponseEntity DeleteGuardian(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
