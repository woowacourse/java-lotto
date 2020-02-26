package lotto.domain.result;

import lotto.domain.money.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    @DisplayName("LottoResult 생성")
    void createLottoResult() {
        Map<Rank, Integer> rankMap = new HashMap<>();
        rankMap.put(Rank.FIFTH, 1);
        LottoResult lottoResult = new LottoResult(rankMap);
    }

    @Test
    @DisplayName("LottoResult는 Rank를 받아서 해당 Rank가 몇개 있는지 반환")
    void countRanks() {
        Map<Rank, Integer> rankMap = new HashMap<>();
        int count = 1000000;
        rankMap.put(Rank.FIRST, count);

        LottoResult lottoResult = new LottoResult(rankMap);

        assertThat(lottoResult.count(Rank.FIRST)).isEqualTo(count);
    }

    @Test
    @DisplayName("LottoResult는 Money를 받아서 수익률을 계산")
    void getProfitRatio() {
        Map<Rank, Integer> rankMap = new HashMap<>();
        rankMap.put(Rank.THIRD, 1);
        rankMap.put(Rank.FOURTH, 1);
        rankMap.put(Rank.FIFTH, 1);

        LottoMoney lottoMoney = new LottoMoney(5000);
        LottoResult lottoResult = new LottoResult(rankMap);

        assertThat(lottoResult.getProfit(lottoMoney)).isEqualTo(31000);
    }
}