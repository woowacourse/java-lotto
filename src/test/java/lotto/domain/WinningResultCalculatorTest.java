package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class WinningResultCalculatorTest {

    @Test
    void 당첨금액_총합() {
        //given
        List<Money> winningMoneys = Arrays.asList(new Money(5_000), new Money(50_000));
        //when & then
        assertThat(WinningResultCalculator.calculateTotalWinningMoney(winningMoneys)).isEqualTo(55_000);
    }
}
