package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseAmountTest {
    @Test
    void 돈을_소유하는_객체_생성() {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(1000, 0);
        assertThat(purchaseAmount).isEqualTo(PurchaseAmount.of(1000, 0));
    }

    @Test
    void 최소금액_이하로_입력하는_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            PurchaseAmount.of(900, 0);
        });
    }

    @Test
    void 거스름_돈이_있는_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            PurchaseAmount.of(1902, 0);
        });
    }

    @Test
    void 로또_자동_구입_개수_반환() {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(2000, 0);
        assertThat(purchaseAmount.purchaseAutoCount()).isEqualTo(2);
    }

    @Test
    void 로또_수동_구입_개수_반환() {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(3000, 1);
        assertThat(purchaseAmount.purchaseManualCount()).isEqualTo(1);
    }
}
