package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Test
    @DisplayName("비즈니스 룰에 따라, 수익 여부를 반환한다.")
    void testCalculateProfit() {
        Profit profit = new Profit(1.5);
        Profit profit2 = new Profit(0.5);

        assertThat(profit.isProfit()).isTrue();
        assertThat(profit2.isProfit()).isFalse();
    }
}
