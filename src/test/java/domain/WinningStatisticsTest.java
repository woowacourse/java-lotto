package domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;


class WinningStatisticsTest {

    @Test
    void 당첨_통계를_산출한다() {
        List<Matcher> matchers = List.of(
                new Matcher(5, false),
                new Matcher(5, true),
                new Matcher(6, true),
                new Matcher(6, false)
        );
        assertThat(WinningStatistics.calculateWinningCount(matchers))
                .contains(
                        new WinningCount(WinningStatistics.FIVE_WITH_BONUS, 1),
                        new WinningCount(WinningStatistics.FIVE, 1),
                        new WinningCount(WinningStatistics.SIX, 2)
                );
    }

    @Test
    void 수익률을_계산한다() {
        int purchaseAmount = 5000;
        List<WinningCount> winningCounts = List.of(
                new WinningCount(WinningStatistics.THREE, 2)
        );
        assertThat(WinningStatistics.calculateYield(purchaseAmount, winningCounts)).isEqualTo(2);
    }
}