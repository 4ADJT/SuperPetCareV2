package br.com.superpetcare.superpetcare.domain.payment;

import br.com.superpetcare.superpetcare.domain.cart.CartEntity;

import java.util.Date;
import java.util.UUID;

public record ResgiterPayment(
        UUID cartId
) {
}
