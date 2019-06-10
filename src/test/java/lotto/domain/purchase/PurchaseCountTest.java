package lotto.domain.purchase;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseCountTest {
    private static final int PURCHASE_AMOUNT = 1000;

    @Test
    void 생성() {
        PurchaseCount purchaseCount = PurchaseCount.of(PurchaseAmount.of(PURCHASE_AMOUNT), 0);
        assertThat(purchaseCount).isEqualTo(PurchaseCount.of(PurchaseAmount.of(PURCHASE_AMOUNT), 0));
    }

    @Test
    void 수동으로_구매할_수_없는_경우() {
        assertThrows(InvalidPurchaseCountException.class, () -> {
            PurchaseCount.of(PurchaseAmount.of(PURCHASE_AMOUNT), 2);
        });

        assertThrows(InvalidPurchaseCountException.class, () -> {
            PurchaseCount.of(PurchaseAmount.of(PURCHASE_AMOUNT), -1);
        });
    }

    @Test
    void 자동_개수_확인() {
        PurchaseCount purchaseCount = PurchaseCount.of(PurchaseAmount.of(PURCHASE_AMOUNT), 1);
        assertThat(purchaseCount.getAutoCount()).isEqualTo(0);
    }

    @Test
    void 수동_개수_확인() {
        PurchaseCount purchaseCount = PurchaseCount.of(PurchaseAmount.of(PURCHASE_AMOUNT), 1);
        assertThat(purchaseCount.getManualCount()).isEqualTo(1);
    }
}
