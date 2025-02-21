package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {
    @Test
    @DisplayName("순위와 횟수가 주어질 때 그 순위에 대한 횟수가 잘 증가한다")
    void should_increase_count_by_winningInfo_and_count() {
        // given
        WinningResult winningResult = new WinningResult();
        WinningInfo winningInfo = WinningInfo.FIRST_PRIZE;
        int count = 2;

        // when
        winningResult.increaseCount(winningInfo, count);

        // when
        assertThat(winningResult.getCount(winningInfo)).isEqualTo(count);
    }

    @Test
    @DisplayName("순위가 주어질 때 그 순위에 대한 등장 횟수를 가져온다")
    void should_return_count_by_winningInfo() {
        // given
        WinningResult winningResult = new WinningResult();
        WinningInfo winningInfo = WinningInfo.FIRST_PRIZE;
        int count = 2;

        // when
        winningResult.increaseCount(winningInfo, count);

        // when
        assertThat(winningResult.getCount(winningInfo)).isEqualTo(count);
    }

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

    @Test
    @DisplayName("로또 구입 금액이 주어졌을 때 수익률을 계산한다")
    void should_return_revenue_given_money() {
        // given
        int moneyValue = 2000;
        Money money = new Money(moneyValue);
        WinningResult winningResult = new WinningResult();
        winningResult.increaseCount(WinningInfo.FIFTH_PRIZE, 2);

        // when
        float result = winningResult.calculateRevenue(money);

        // then
        float excepted = (WinningInfo.FIFTH_PRIZE.getPrice() * 2) / moneyValue;
        assertThat(result).isEqualTo(excepted);
    }

}