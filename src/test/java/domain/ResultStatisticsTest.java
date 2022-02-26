package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResultStatisticsTest {

    private static ResultStatistics resultStatistics;
    private static final int FIRST_COUNT = 1;
    private static final int SECOND_COUNT = 2;
    private static final int THIRD_COUNT = 3;
    private static final int FIRST_PRIZE = 2_000_000_000;
    private static final int SECOND_PRIZE = 30_000_000;
    private static final int THIRD_PRIZE = 1_500_000;

    @BeforeEach
    void setUp() {
        resultStatistics = new ResultStatistics(createResults());
    }

    @Test
    void constructor_initsWithLottoResults() {
        Map<LottoResult, Integer> stats = resultStatistics.getResultStatistics();

        assertThat(stats.keySet().toArray())
                .isEqualTo(LottoResult.values());

        assertThat(stats.get(LottoResult.FIRST)).isEqualTo(FIRST_COUNT);
        assertThat(stats.get(LottoResult.SECOND)).isEqualTo(SECOND_COUNT);
        assertThat(stats.get(LottoResult.THIRD)).isEqualTo(THIRD_COUNT);
        assertThat(stats.get(LottoResult.FOURTH)).isEqualTo(0);
        assertThat(stats.get(LottoResult.FIFTH)).isEqualTo(0);
    }

    @Test
    void calculateTotalPrize() {
      int totalPrize = resultStatistics.calculateTotalPrize();

        assertThat(totalPrize).isEqualTo(defaultTotalPrize());
    }

    @Test
    void getResultStatistics_throwExceptionOnModify() {
        Map<LottoResult, Integer> stats = resultStatistics.getResultStatistics();

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> stats.put(LottoResult.FIRST, 10));
    }

    private List<LottoResult> createResults() {
        List<LottoResult> results = new ArrayList<>();
        for (int i = 0; i < FIRST_COUNT; i++) {
            results.add(LottoResult.FIRST);
        }
        for (int i = 0; i < SECOND_COUNT; i++) {
            results.add(LottoResult.SECOND);
        }
        for (int i = 0; i < THIRD_COUNT; i++) {
            results.add(LottoResult.THIRD);
        }
        return results;
    }

    private int defaultTotalPrize() {
        int totalPrize = 0;
        totalPrize += FIRST_COUNT * FIRST_PRIZE;
        totalPrize += SECOND_COUNT * SECOND_PRIZE;
        totalPrize += THIRD_COUNT * THIRD_PRIZE;
        return totalPrize;
    }
}