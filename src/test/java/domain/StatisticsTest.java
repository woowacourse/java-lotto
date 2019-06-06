package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class StatisticsTest {
    private Statistics statistics;

    @BeforeEach
    void setUp() {
        statistics = Statistics.of(Rank.values());
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

    @Test
    void 총_수익률을_제대로_계산하는지_테스트() {
        statistics.add(Rank.FIFTH);
        assertThat(statistics.calculateEarningRates()).isEqualTo(5, offset(0.00099));
    }
}
