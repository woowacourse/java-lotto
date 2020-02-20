package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TotalWinningMoneyCalculatorTest {

    @Test
    void calculate() {
        //given
        List<Integer> winningMoneys = Arrays.asList(5_000, 50_000);
        //when & then
        assertThat(TotalWinningMoneyCalculator.calculate(winningMoneys)).isEqualTo(55_000);
    }
}
