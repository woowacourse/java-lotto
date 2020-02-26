package lotto.domain.result;

import lotto.domain.money.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    @DisplayName("LottoResult 생성")
    void createLottoResult() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.FIFTH);
        LottoResult lottoResult = new LottoResult(ranks);
    }

    @Test
    @DisplayName("LottoResult는 Rank를 받아서 해당 Rank가 몇개 있는지 반환")
    void countRanks() {
        int count = 1000000;
        List<Rank> ranks = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            ranks.add(Rank.FIRST);
        }

        LottoResult lottoResult = new LottoResult(ranks);

        assertThat(lottoResult.count(Rank.FIRST)).isEqualTo(count);
    }

    @Test
    @DisplayName("LottoResult는 Money를 받아서 수익률을 계산")
    void getProfitRatio() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.THIRD);
        ranks.add(Rank.FOURTH);
        ranks.add(Rank.FIFTH);

        LottoMoney lottoMoney = new LottoMoney(5000);
        LottoResult lottoResult = new LottoResult(ranks);

        assertThat(lottoResult.getProfit(lottoMoney)).isEqualTo(31000);
    }
}