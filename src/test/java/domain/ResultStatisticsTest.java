package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResultStatisticsTest {

    private static ResultStatistics resultStatistics;

    @BeforeEach
    void setUp() {
        resultStatistics = new ResultStatistics();
    }

    @Test
    void constructor_initsWithLottoResultValues() {
        Set<LottoResult> results = Arrays.stream(LottoResult.values()).collect(Collectors.toSet());

        assertThat(resultStatistics.getLottoResultKeys()).isEqualTo(results);
        assertThat(resultStatistics.calculateTotalPrize()).isEqualTo(0);
    }

    @Test
    void updateStats_updatesStatsWithEachLottoResult() {
        List<LottoResult> results = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            results.add(LottoResult.FIRST);
        }

        resultStatistics.updateStats(results, LottoResult.FIRST);

        Map<LottoResult, Integer> actual = resultStatistics.getResultStatistics();
        assertThat(actual.get(LottoResult.FIRST)).isEqualTo(3);
        assertThat(actual.get(LottoResult.SECOND)).isEqualTo(0);
    }

    @Test
    void updateStats_doesNotUpdatesStatsOnLottoResultNotMatching() {
        List<LottoResult> results = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            results.add(LottoResult.SECOND);
        }

        resultStatistics.updateStats(results, LottoResult.FIRST);

        Map<LottoResult, Integer> actual = resultStatistics.getResultStatistics();
        assertThat(actual.get(LottoResult.FIRST)).isEqualTo(0);
        assertThat(actual.get(LottoResult.SECOND)).isEqualTo(0);
    }
}