package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPurchasingMoneyTest {
    @Test
    @DisplayName("로또는 최소 천원이 있어야 구매할 수 있다.")
    void underLottoTicketPrice() {
        // given
        int notEnoughAmount = 999;

        // then
        assertThatThrownBy(() -> LottoPurchasingMoney.valueOf(notEnoughAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또를 구매하려면 최소 천원 이상 투입해야 합니다.");
    }
}
