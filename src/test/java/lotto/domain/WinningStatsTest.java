package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatsTest {

    @Test
    @DisplayName("당첨 등수가 나오면 해당 등수의 개수를 +1 한다")
    void putLottoRank() {
        // given
        WinningStats winningStats = new WinningStats();
        LottoRank rank = LottoRank.getRank(6, true);

        // when
        winningStats.put(rank);
        int result = winningStats.get(rank);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("당첨 금액의 총합을 반환한다")
    void getTotalPrize() {
        // given
        WinningStats winningStats = new WinningStats();
        LottoRank lottoRank = LottoRank.getRank(6, true);

        // when
        winningStats.put(lottoRank);
        winningStats.put(lottoRank);
        long result = winningStats.getTotalPrize();

        // then
        assertThat(result).isEqualTo(4_000_000_000L);
    }

    @Test
    @DisplayName("수익률을 반환한다")
    void getEarningsRate() {
        // given
        WinningStats winningStats = new WinningStats();
        LottoRank lottoRank = LottoRank.getRank(3, true);
        int money = 10000;

        // when
        winningStats.put(lottoRank);
        double result = winningStats.getEarningsRate(money);

        // then
        assertThat(result).isEqualTo(0.5);
    }
}
