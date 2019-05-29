package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.exceptions.PurchaseAmountException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseAmountTest {
    @Test
    void purchase_amount_lower_bound() {
        assertThrows(PurchaseAmountException.class, () -> {
            PurchaseAmount.of("999");
        });
    }
}
