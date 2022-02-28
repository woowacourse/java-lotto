package lotto.model.result;

import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RateOfReturnTest {

    @ParameterizedTest
    @ValueSource(ints = {10000, 14000, 50000})
    @DisplayName("수익률 검증")
    void checkProfit(int money) {
        RateOfReturn rateOfReturn = new RateOfReturn(Integer.toString(money));

        WinningResult winningResult = new WinningResult();
        winningResult.addCount(Rank.FOUR);

        assertThat(rateOfReturn.calcRateOfReturn(winningResult)).isEqualTo(Rank.FOUR.getValue()  / (double) money);
    }
}
