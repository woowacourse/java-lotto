package lotto;

import domain.LottoRank;
import domain.LottoResult;
import domain.Profit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {

    @Test
    void 총_수익_계산_테스트() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.addWinningRankCount(LottoRank.THIRD);
        lottoResult.addWinningRankCount(LottoRank.FOURTH);
        lottoResult.addWinningRankCount(LottoRank.FIFTH);
        lottoResult.addWinningRankCount(LottoRank.FIFTH);
        int totalProfit = lottoResult.calculateProfit();

        Profit profit = new Profit();
        int profitRatio = profit.calculateProfitRatio(totalProfit, 4);
        assertThat(profitRatio).isEqualTo(390);
    }
}
