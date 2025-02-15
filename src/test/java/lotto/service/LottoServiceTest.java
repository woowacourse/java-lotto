package lotto.service;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.Cashier;
import lotto.domain.MatchInfo;
import lotto.domain.Profit;

class LottoServiceTest {

    @Test
    @DisplayName("수익률 계산 테스트")
    void testConvertToMapProfit() {
        HashMap<MatchInfo, Integer> map = new HashMap<>();
        Cashier cashier = new Cashier(10000);

        map.put(MatchInfo.MATCH_THREE, 3);

        LottoService lottoService = new LottoService();
        Profit profit = lottoService.calculateProfit(map, cashier);

        assertThat(profit.rate()).isEqualTo(1.5);
    }
}
