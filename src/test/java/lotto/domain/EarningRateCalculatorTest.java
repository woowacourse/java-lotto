package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EarningRateCalculatorTest {

    @Test
    void 수익률_계산() {
        //given
        double totalWinningMoney = 5_000;
        int purchaseAmount = 14_000;
        //when & then
        assertThat(EarningRateCalculator.calculate(totalWinningMoney, purchaseAmount)).isEqualTo(35);
    }
}
