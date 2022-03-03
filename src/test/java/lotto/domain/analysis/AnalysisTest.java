package lotto.domain.analysis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.money.Money;
import lotto.utils.Rank;

class AnalysisTest {

    @DisplayName("당첨 통계의 당첨 등수 개수는 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 당첨 등수 개수 : {2}")
    @MethodSource("provideForCalculateTest")
    void calculateRankCountsTest(final List<Rank> ranks,
                                 final Money money,
                                 final Map<Rank, Long> expectedRankCounts,
                                 final double expectedProfitRate) {
        final Analysis analysis = new Analysis(ranks, money);
        final Map<Rank, Long> actualRankCounts = analysis.getRankCounts();
        assertThat(actualRankCounts).isEqualTo(expectedRankCounts);
    }

    @DisplayName("당첨 통계의 수익률은 기댓값과 일치해야 한다.")
    @ParameterizedTest(name = "[{index}] 수익률 : {3}")
    @MethodSource("provideForCalculateTest")
    void calculateProfitRateTest(final List<Rank> ranks,
                                 final Money money,
                                 final Map<Rank, Long> expectedRankCounts,
                                 final double expectedProfitRate) {
        final Analysis analysis = new Analysis(ranks, money);
        final double actualProfitRate = analysis.getProfitRate();
        assertThat(actualProfitRate).isEqualTo(expectedProfitRate);
    }

    public static Stream<Arguments> provideForCalculateTest() {
        return Stream.of(
                Arguments.of(
                        Named.of("5등 1개",
                                List.of(
                                        Rank.FIFTH_GRADE
                                )
                        ), new Money(5000),
                        Map.of(
                                Rank.FIFTH_GRADE, 1L
                        ), 1.00
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
                        ), new Money(5000),
                        Map.of(
                                Rank.SECOND_GRADE, 1L,
                                Rank.FOURTH_GRADE, 2L,
                                Rank.FIFTH_GRADE, 2L
                        ), 6022.00
                )
        );
    }

}
