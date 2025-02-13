package domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;


class WinningStatisticsTest {

    @Test
    void 당첨_통계를_산출한다() {
        List<MatchDto> matchDtos = List.of(
                new MatchDto(5, false),
                new MatchDto(5, true)
        );
        assertThat(WinningStatistics.calculateWinningCountDtos(matchDtos))
                .contains(
                        new WinningCountDto(WinningStatistics.FIVE_WITH_BONUS, 1),
                        new WinningCountDto(WinningStatistics.FIVE, 1)
                );
    }
}