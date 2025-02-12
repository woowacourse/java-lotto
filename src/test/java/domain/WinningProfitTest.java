package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningProfitTest {

    @DisplayName("총 당첨 금액 계산을 성공한다")
    @Test
    void calculateTotalPriceTest() {
        final Map<LottoRank, Integer> map = Map.of(LottoRank.RANK_4, 1, LottoRank.RANK_5, 1);
        final WinningProfit winningProfit = new WinningProfit(map);
        final int totalPrice = winningProfit.calculateTotalPrice();
        assertThat(totalPrice).isEqualTo(55_000);
    }

    @DisplayName("총 수익률 계산을 성공한다")
    @Test
    void calculateProfitRateTest() {
        final Map<LottoRank, Integer> map = Map.of(LottoRank.RANK_5, 1);
        final WinningProfit winningProfit = new WinningProfit(map);
        final double rate = winningProfit.calculateProfitRate(14_000);
        assertThat(Math.floor(rate * 100) / 100.0).isEqualTo(0.35);
    }
}
