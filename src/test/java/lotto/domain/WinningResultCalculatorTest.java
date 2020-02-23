package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class WinningResultCalculatorTest {

    @Test
    void 수익률_계산() {
        //given
        double totalWinningMoney = 5_000;
        int purchaseAmount = 14_000;
        //when & then
        assertThat(WinningResultCalculator.calculateEarningRate(totalWinningMoney, purchaseAmount)).isEqualTo(35);
    }

    @Test
    void 당첨금액_총합() {
        //given
        List<Integer> winningMoneys = Arrays.asList(5_000, 50_000);
        //when & then
        assertThat(WinningResultCalculator.calculateTotalWinningMoney(winningMoneys)).isEqualTo(55_000);
    }
}
