package lotto.domain.purchase;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseAmountTest {
    @Test
    void 돈을_소유하는_객체_생성() {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(1000);
        assertThat(purchaseAmount).isEqualTo(PurchaseAmount.of(1000));
    }

    @Test
    void 최소금액_이하로_입력하는_경우() {
        assertThrows(InvalidPurchaseAmountException.class, () -> {
            PurchaseAmount.of(900);
        });
    }

    @Test
    void 거스름_돈이_있는_경우() {
        assertThrows(InvalidPurchaseAmountException.class, () -> {
            PurchaseAmount.of(1902);
        });
    }
}
