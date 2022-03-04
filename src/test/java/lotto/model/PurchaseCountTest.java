package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseCountTest {
    public static final int LOTTO_PRICE = 1000;

    @DisplayName("10장 중 3장이 수동이면 자동 로또는 7장이다")
    @Test
    void count_10_manual_3() {
        int count = 10;
        int manualCount = 3;
        PurchaseCount purchaseCount = PurchaseCount.of(
                Money.from(String.valueOf(count * LOTTO_PRICE)), String.valueOf(manualCount));
        for (int i = 0; i < (count - manualCount); i++) {
            purchaseCount.subtractAuto();
        }

        assertThat(purchaseCount.isAutoAvailable()).isTrue();
    }
}
