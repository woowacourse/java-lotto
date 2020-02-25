package lotto.domain.result;

import lotto.domain.money.LottoMoney;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.result.RankTest.getRanksFixture;
import static lotto.domain.result.RankTest.getRanksFromThirdToFifthFixture;
import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    void testLottoResult() {
        List<Rank> ranks = getRanksFixture();
        LottoResult lottoResult = new LottoResult(ranks);
    }

    @Test
    void countRanks() {
        int count = 1000000;
        List<Rank> ranks = getRanksFixture();
        LottoResult lottoResult = new LottoResult(ranks);

        assertThat(lottoResult.count(Rank.FIRST)).isEqualTo(count);
    }

    @Test
    void getProfitRatio() {
        List<Rank> ranks = getRanksFromThirdToFifthFixture();
        LottoMoney lottoMoney = new LottoMoney(5000);
        LottoResult lottoResult = new LottoResult(ranks);

        assertThat(lottoResult.getProfit(lottoMoney)).isEqualTo(31000);
    }
}