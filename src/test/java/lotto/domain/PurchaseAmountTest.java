package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchaseAmountTest {
    @Test
    void 금액_생성_실패() {
        assertThrows(PurchaseAmountException.class, () -> {
            new PurchaseAmount(999);
        });
    }

    @Test
    void 거스름돈_확인() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(1300);
        purchaseAmount.buy(1000);
        assertThat(purchaseAmount.available()).isEqualTo(300);
    }

}