package domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;


class WinningStatisticsTest {

    @Test
    void 당첨_통계를_산출한다() {
        List<MatchDto> matchDtos = List.of(
                new MatchDto(5, false),
                new MatchDto(5, true),
                new MatchDto(6, true),
                new MatchDto(6, false)
        );
        assertThat(WinningStatistics.calculateWinningCountDtos(matchDtos))
                .contains(
                        new WinningCountDto(WinningStatistics.FIVE_WITH_BONUS, 1),
                        new WinningCountDto(WinningStatistics.FIVE, 1),
                        new WinningCountDto(WinningStatistics.SIX, 2)
                );
    }

    @Test
    void 수익률을_계산한다() {
        int purchaseAmount = 5000;
        List<WinningCountDto> winningCountDtos = List.of(
                new WinningCountDto(WinningStatistics.THREE, 2)
        );
        assertThat(WinningStatistics.calculateYield(purchaseAmount, winningCountDtos)).isEqualTo(2);
    }
}