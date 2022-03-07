package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCartTest {
    public static final int LOTTO_PRICE = 1000;

    @DisplayName("수동 로또를 정한 개수만큼 다 사면 더 이상 살 수 없다")
    @Test
    void manual_not_available() {
        int manualCount = 3;
        PurchaseCount purchaseCount = PurchaseCount.of(
                Money.from(String.valueOf(manualCount * LOTTO_PRICE)), String.valueOf(manualCount));
        LottoCart lottoCart = new LottoCart(purchaseCount);
        for (int i = 0; i < manualCount; i++) {
            lottoCart.addManualLotto(List.of("1", "2", "3", "4", "5", "6"));
        }

        assertThat(lottoCart.isManualAvailable()).isFalse();
    }
}
