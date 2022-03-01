package model;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("수동 구매 개수와 자동 구매 개수를 합치면 전체 구매 가능 개수가 된다.")
    void validateTotalCount() {
        // given
        LottoPurchasingMoney purchasingMoney = LottoPurchasingMoney.valueOf(8000);
        int manualCount = 5;
        int expected = 8;

        // when
        LottoOrder lottoOrder = new LottoOrder(purchasingMoney, manualCount);
        int actual = lottoOrder.getAutoLottoCount() + lottoOrder.getManualLottoCount();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}