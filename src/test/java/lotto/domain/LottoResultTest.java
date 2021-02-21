package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        Map<LottoRank, Long> statisticsMap = new HashMap<>();
        statisticsMap.put(LottoRank.FIRST_PRIZE, 1L);
        lottoResult = new LottoResult(statisticsMap);
    }

    @DisplayName("로또 통계에서 각 순위별 당첨 티켓 개수를 가져온다. 없는 경우 0을 반환한다.")
    @Test
    void getCounts() {
        assertThat(lottoResult.getTicketCountsByRank(LottoRank.FIRST_PRIZE)).isEqualTo(1);
        assertThat(lottoResult.getTicketCountsByRank(LottoRank.SECOND_PRIZE)).isZero();
    }

    @DisplayName("구매 금액과 비교해 수익률을 계산한다.")
    @Test
    void calculateYield() {
        double yield = lottoResult.calculateYield(35000);

        assertThat(yield).isEqualTo(((double) 2000000000) / 35000);
    }

    @DisplayName("LottoResult가 반환하는 EntrySet의 Key는 LottoRank 열거형들로 초기화된 상태이다")
    @Test
    void getStatistics() {
        Map<LottoRank, Long> statistics = lottoResult.getStatistics();

        int keyCounts = statistics.size();
        int lottoRankCounts = LottoRank.values().length;

        assertThat(keyCounts).isEqualTo(lottoRankCounts);
    }
}
