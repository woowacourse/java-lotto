package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {
    @Test
    @DisplayName("당첨 결과를 토대로 총 당첨 금액을 가져온다")
    void should_return_total_prices() {
        // given
        WinningResult winningResult = new WinningResult();

        // when
        long result = winningResult.getTotalPrices();

        // then
        long expected = 0L;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 결과를 토대로 총 당첨 금액을 가져온다")
    void should_return_total_prices2() {
        // given
        WinningResult winningResult = new WinningResult();
        final int count = 10;
        winningResult.increaseCount(WinningInfo.FIRST_PRIZE, count);

        // when
        long result = winningResult.getTotalPrices();

        // then
        long expected = WinningInfo.FIRST_PRIZE.getPrice() * count;
        assertThat(result).isEqualTo(expected);
    }
}