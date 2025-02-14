package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Test
    @DisplayName("당첨금이 5_000원, 소비금이 10_000원일 때 수익률은 0.5이다")
    void profitRate_half() {
        // given
        long winningPrize = 5000L;
        long spentMoney = 10000L;

        // when
        Profit profit = Profit.from(winningPrize, spentMoney);

        // then
        assertThat(profit.rate()).isEqualTo(0.5);
    }

}
