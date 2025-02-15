package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningStatisticsTest {
    private WinningStatistics winningStatistics;

    @BeforeEach
    void setUp() {
        Map<Rank, Integer> statistics = new EnumMap<>(Map.of(
                Rank.NONE, 0,
                Rank.FIFTH, 1,
                Rank.FOURTH, 0,
                Rank.THIRD, 0,
                Rank.SECOND, 0,
                Rank.FIRST, 0
        ));

        winningStatistics = new WinningStatistics(statistics);
    }

    @DisplayName("소수점 셋째자리에서 버림한 수익률을 반환한다")
    @Test
    void 소수점_셋째자리에서_버림한_수익률을_반환한다() {
        assertThat(winningStatistics.calculateReturnRate(5)).isEqualTo(1.00);
    }

    @DisplayName("당첨 등수의 개수를 반환한다")
    @Test
    void 당첨_등수의_개수를_반환한다() {
        assertThat(winningStatistics.getRankCount(Rank.FIFTH)).isEqualTo(1);
    }
}
