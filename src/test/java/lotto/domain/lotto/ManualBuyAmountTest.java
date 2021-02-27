package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualBuyAmountTest {

    @Test
    @DisplayName("실패 - 수동구매수 > 총구매수")
    void getInstance_fail() {
        assertThatThrownBy(() -> ManualBuyAmount.getInstance("2", Money.valueOf("1000")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("올바른 수동구매수 반환")
    void getAmount() {
        ManualBuyAmount manualBuyAmount = ManualBuyAmount.getInstance("1", Money.valueOf("1000"));

        assertThat(manualBuyAmount.getAmount()).isEqualTo(1);
    }
}