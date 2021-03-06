package lotto.ranking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatisticsTest {
    @Test
    @DisplayName("당첨 통계 생성")
    void statisticsCreate() {
        Statistics statistics = new Statistics();
        statistics.calculateStatistics(Arrays.asList(Ranking.FIRST, Ranking.FORTH, Ranking.FORTH, Ranking.FORTH));
        assertThat(statistics.findRankingCount(Ranking.FIRST)).isEqualTo(1);
        assertThat(statistics.findRankingCount(Ranking.SECOND)).isEqualTo(0);
        assertThat(statistics.findRankingCount(Ranking.THIRD)).isEqualTo(0);
        assertThat(statistics.findRankingCount(Ranking.FORTH)).isEqualTo(3);
    }
}
