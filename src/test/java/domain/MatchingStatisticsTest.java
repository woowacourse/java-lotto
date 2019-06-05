package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchingStatisticsTest {
    private MatchingStatistics statistics;

    @BeforeEach
    void setUp() {
        statistics = MatchingStatistics.of(Rank.values());
    }

    @Test
    void 당첨_통계가_생성될_때_0으로_초기화되는지_테스트() {
        assertThat(statistics.countsOf(Rank.FIRST)).isEqualTo(0);
    }

    @Test
    void 당첨_통계에_당첨_결과가_제대로_기록되는지_테스트() {
        Rank testResult = Rank.FIRST;

        statistics.add(testResult);
        assertThat(statistics.countsOf(Rank.FIRST)).isEqualTo(1);
    }
}
