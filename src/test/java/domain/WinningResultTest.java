package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    @Test
    void 수익률을_계산할_수_있다() {
        //given
        WinningResult winningResult = new WinningResult(
                new Money(2000),
                Map.of(
                        Rank.FIFTH, 1,
                        Rank.FOURTH, 1)
        );

        //when
        double rateOfReturn = winningResult.calculateRateOfReturn();

        //then
        assertThat(rateOfReturn).isEqualTo(27.5);
    }
}