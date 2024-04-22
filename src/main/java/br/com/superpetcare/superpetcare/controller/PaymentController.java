package br.com.superpetcare.superpetcare.controller;

import br.com.superpetcare.superpetcare.domain.cart.CartRepository;
import br.com.superpetcare.superpetcare.domain.cart.DetailCart;
import br.com.superpetcare.superpetcare.domain.cart.ServiceCart;
import br.com.superpetcare.superpetcare.domain.payment.*;
import br.com.superpetcare.superpetcare.domain.services.DetailService;
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
import java.util.UUID;

@RestController
@RequestMapping("/payment")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Payment of Cart API")
public class PaymentController {

    @Autowired
    ServiceCart serviceCart;

    @Autowired
    ServicePayment servicePayment;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ComponentPayment componentPayment;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra pagamento", description = "Método responsável registar o pagamento.")
    public ResponseEntity<DetailPayment> registerPayment(@RequestBody @Valid ResgiterPayment resgiterPayment, UriComponentsBuilder componentsBuilder) {
        var payment = componentPayment.registerPayment(resgiterPayment);
        var uri = componentsBuilder.path("payment/{id}").buildAndExpand(payment.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailPayment(payment));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar pagamento", description = "Método responsável exibir detalhes do pagamento.")
    public ResponseEntity<DetailPayment> detailPayment(@PathVariable UUID id) {
        PaymentEntity paymentEntity = paymentRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetailPayment(paymentEntity));
    }

    @GetMapping
    @Operation(summary = "Consultar pagamentos", description = "Método responsável listar pagamentos.")
    public ResponseEntity<Page<SimplePayment>> listPayments(@PageableDefault(size = 10) Pageable pageable) {
        var page = paymentRepository.findAll(pageable).map(SimplePayment::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/cart/{cartId}")
    @Operation(summary = "previaPagamento", description = "Método responsável exibir a previa do pagamento.")
    public ResponseEntity<DetailPayment> previewPayment(@PathVariable UUID cartId) {

        DetailPayment payment = componentPayment.previewPayment(cartId);

        return ResponseEntity.ok(payment);

    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualziar pagameneto", description = "Método responsável pro atualizar o status do pagamento.")
    public ResponseEntity<DetailPayment> updatePayment(@PathVariable UUID id, @RequestBody @Valid UpdatePayment updatePayment) {
        var payment = paymentRepository.getReferenceById(id);
        payment.update(updatePayment);
        return ResponseEntity.ok(new DetailPayment(payment));
    }

}
