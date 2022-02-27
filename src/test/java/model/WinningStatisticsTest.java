package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {
    @Test
    @DisplayName("당첨 등수가 나오면 해당 등수의 개수를 +1 한다")
    void putLottoRank() {
        // given
        WinningStatistics winningStatistics = new WinningStatistics();
        LottoRank rank = LottoRank.getRank(6, true);

        // when
        winningStatistics.put(rank);
        int result = winningStatistics.get(rank);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("당첨 금액의 총합을 반환한다")
    void getTotalPrize() {
        // given
        WinningStatistics winningStatistics = new WinningStatistics();
        LottoRank lottoRank = LottoRank.getRank(6, true);

        // when
        winningStatistics.put(lottoRank);
        winningStatistics.put(lottoRank);
        long result = winningStatistics.getTotalPrize();

        // then
        assertThat(result).isEqualTo(4_000_000_000L);
    }

    @Test
    @DisplayName("수익률을 반환한다")
    void getEarningsRate() {
        // given
        WinningStatistics winningStatistics = new WinningStatistics();
        LottoRank lottoRank = LottoRank.getRank(3, true);
        int money = 10000;

        // when
        winningStatistics.put(lottoRank);
        double result = winningStatistics.getEarningsRate(money);

        // then
        assertThat(result).isEqualTo(0.5);
    }
}
