package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.rank.Rank;

class AnalysisTest {

    @DisplayName("당첨 통계의 당첨 등수 개수는 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 개수 : {2}")
    @MethodSource("provideForCalculateTest")
    void calculateRankCountsTest(final List<Rank> ranks,
                                 final int money,
                                 final Map<Rank, Integer> rankCounts,
                                 final double profitRate) {
        final Analysis analysis = new Analysis(ranks, money);
        assertThat(analysis.getRankCounts()).isEqualTo(rankCounts);
    }

    @DisplayName("당첨 통계의 수익률은 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 수익률 : {3}")
    @MethodSource("provideForCalculateTest")
    void calculateProfitRateTest(final List<Rank> ranks,
                                 final int money,
                                 final Map<Rank, Integer> rankCounts,
                                 final double profitRate) {
        final Analysis analysis = new Analysis(ranks, money);
        assertThat(analysis.getProfitRate()).isEqualTo(profitRate);
    }

    public static Stream<Arguments> provideForCalculateTest() {
        return Stream.of(
                Arguments.of(
                        Named.of("5등 1개",
                                List.of(
                                        Rank.FIFTH_GRADE
                                )
                        ), 50000,
                        Map.of(
                                Rank.FIRST_GRADE, 0,
                                Rank.SECOND_GRADE, 0,
                                Rank.THIRD_GRADE, 0,
                                Rank.FOURTH_GRADE, 0,
                                Rank.FIFTH_GRADE, 1
                        ), 0.1
                ),
                Arguments.of(
                        Named.of("2등 1개, 4등 2개, 5등 2개",
                                List.of(
                                        Rank.FOURTH_GRADE,
                                        Rank.FIFTH_GRADE,
                                        Rank.SECOND_GRADE,
                                        Rank.FIFTH_GRADE,
                                        Rank.FOURTH_GRADE
                                )
                        ), 5000,
                        Map.of(
                                Rank.FIRST_GRADE, 0,
                                Rank.SECOND_GRADE, 1,
                                Rank.THIRD_GRADE, 0,
                                Rank.FOURTH_GRADE, 2,
                                Rank.FIFTH_GRADE, 2
                        ), 6022.0
                )
        );
    }

}
