package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsTest {

    private LottoStatistics lottoStatistics;

    @BeforeEach
    void setUp() {
        Map<LottoRank, Long> statisticsMap = new HashMap<>();
        statisticsMap.put(LottoRank.FIRST, 1L);
        lottoStatistics = new LottoStatistics(statisticsMap);
    }

    @DisplayName("로또 통계에서 각 순위별 당첨 티켓 개수를 가져온다. 없는 경우 0을 반환한다.")
    @Test
    void getCounts() {
        assertThat(lottoStatistics.getCounts(LottoRank.FIRST)).isEqualTo(1);
        assertThat(lottoStatistics.getCounts(LottoRank.SECOND)).isEqualTo(0);
    }

    @DisplayName("구매 금액과 비교해 수익률을 계산한다.")
    @Test
    void calculateYield() {
        Money money = new Money(35477);

        double yield = lottoStatistics.calculateYield(money);

        assertThat(yield).isEqualTo(((double)2000000000) / 35477);
    }
}