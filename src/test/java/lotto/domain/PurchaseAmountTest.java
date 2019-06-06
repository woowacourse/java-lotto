package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.exceptions.PurchaseAmountException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseAmountTest {
    @Test
    void purchase_amount_lower_bound() {
        assertThrows(PurchaseAmountException.class, () -> {
            PurchaseAmount.of(999);
        });
    }

    @Test
    void purchase_amount_not_divisible_price_unit_1000() {
        assertThrows(PurchaseAmountException.class, () -> {
            PurchaseAmount.of(1002);
        });
    }
}
