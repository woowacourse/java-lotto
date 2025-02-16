package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningStatisticsTest {
    @DisplayName("구입 금액에 따른 수익률을 반환할 수 있다")
    @CsvSource(value = {"5000:22.00", "50000:2.20", "100000:1.10"}, delimiterString = ":")
    @ParameterizedTest
    void 구입_금액에_따른_수익률을_반환할_수_있다(int purchaseAmount, double expected) {
        Map<Rank, Integer> statistics = new HashMap<>();
        statistics.put(Rank.FIFTH, 2);
        statistics.put(Rank.FOURTH, 2);
        WinningStatistics winningStatistics = new WinningStatistics(statistics);

        assertThat(winningStatistics.calculateReturnRate(purchaseAmount)).isEqualTo(expected);
    }
}
