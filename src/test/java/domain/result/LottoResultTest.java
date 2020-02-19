package domain.result;

import static domain.result.RankTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import domain.money.LottoMoney;

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