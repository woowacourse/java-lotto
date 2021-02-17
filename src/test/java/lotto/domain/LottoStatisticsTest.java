package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoStatisticsTest {

    private Map<LottoRank, Long> statisticsMap;

    @BeforeEach
    void setUp() {
        statisticsMap = new HashMap<>();
        statisticsMap.put(LottoRank.FIRST, 1L);
    }

    @DisplayName("로또 통계에서 각 순위별 당첨 티켓 개수를 가져온다. 없는 경우 0을 반환한다.")
    @Test
    void getCounts() {
        LottoStatistics lottoStatistics = new LottoStatistics(statisticsMap);
        assertThat(lottoStatistics.getCount(LottoRank.FIRST)).isEqualTo(1);
        assertThat(lottoStatistics.getCount(LottoRank.SECOND)).isEqualTo(0);
    }
}