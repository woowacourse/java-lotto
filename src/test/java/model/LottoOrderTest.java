package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoOrderTest {
    @Test
    @DisplayName("수동 구매 개수가 전체 구매 가능 개수보다 크면 예외를 반환한다.")
    void invalidManualCount() {
        // given
        LottoPurchasingMoney purchasingMoney = LottoPurchasingMoney.valueOf(2000);
        int manualCount = 3;

        // then
        assertThatThrownBy(() -> new LottoOrder(purchasingMoney, manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoOrder.TOO_MANY_MANUAL);
    }
}