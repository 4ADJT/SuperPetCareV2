package br.com.superpetcare.superpetcare.domain.payment;

import br.com.superpetcare.superpetcare.domain.cart.CartEntity;
import br.com.superpetcare.superpetcare.domain.payment.dao.UpdatePayment;
import br.com.superpetcare.superpetcare.domain.services.ServiceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "payment")
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private CartEntity cart;
    private double totalAmount;
    private PaymentStatus paymentStatus;
    private Date paymentDate;

    public PaymentEntity(CartEntity cartEntity) {
        this.cart = cartEntity;
        this.totalAmount = cartEntity.getServices()
                .stream().mapToDouble(ServiceEntity::getPrice).sum();
        this.paymentStatus = PaymentStatus.PENDING;
        this.paymentDate = new Date();
    }

    public void update(UpdatePayment updatePayment) {
        this.paymentStatus = updatePayment.paymentStatus();
    }
}
