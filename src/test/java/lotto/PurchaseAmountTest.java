package lotto;

import org.junit.jupiter.api.Test;

import lotto.exceptions.InvalidPurchaseAmountException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseAmountTest {
    @Test
    void purchase_amount_lower_bound() {
        assertThrows(InvalidPurchaseAmountException.class, () -> {
            PurchaseAmount.is(999);
        });
    }

    @Test
    void purchase_amount_should_divisible_by_1000() {
        assertThrows(InvalidPurchaseAmountException.class, () -> {
            PurchaseAmount.is(1430);
        });
    }

    @Test
    void get_LottoGame_counts_from_purchase_amount() {
        assertThat(PurchaseAmount.is(3000).getGameCounts()).isEqualTo(3);
    }
}
