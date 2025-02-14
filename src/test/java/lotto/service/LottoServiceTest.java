package lotto.service;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.Money;
import lotto.domain.MatchStatistics;
import lotto.dto.Profit;

class LottoServiceTest {

    @Test
    @DisplayName("수익률 계산 테스트")
    void testConvertToMapProfit() {
        HashMap<MatchStatistics, Integer> map = new HashMap<>();
        Money money = new Money(10000);

        map.put(MatchStatistics.MATCH_THREE, 3);

        LottoService lottoService = new LottoService();
        Profit profit = lottoService.calculateProfit(map, money);

        assertThat(profit.rate()).isEqualTo(1.5);
    }
}