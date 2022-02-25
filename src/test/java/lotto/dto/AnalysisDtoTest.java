package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.rank.Rank;

class AnalysisDtoTest {

    @DisplayName("통계 생성, 당첨 등수별 개수 확인")
    @ParameterizedTest(name = "[{index}] {1}원 : {0}")
    @MethodSource("provideForCalculateTest")
    void calculateRankCountsTest(final List<Rank> ranks,
                                 final int money,
                                 final Map<Rank, Integer> rankCounts,
                                 final double profitRate) {
        final AnalysisDto analysis = new AnalysisDto(ranks, money);
        assertThat(analysis.getRankCounts()).isEqualTo(rankCounts);
    }

    @DisplayName("통계 생성, 수익률 계산 결과 확인")
    @ParameterizedTest(name = "[{index}] {1}원 : {0}")
    @MethodSource("provideForCalculateTest")
    void calculateProfitRateTest(final List<Rank> ranks,
                                 final int money,
                                 final Map<Rank, Integer> rankCounts,
                                 final double profitRate) {
        final AnalysisDto analysis = new AnalysisDto(ranks, money);
        assertThat(analysis.getProfitRate()).isEqualTo(profitRate);
    }

    public static Stream<Arguments> provideForCalculateTest() {
        return Stream.of(
                Arguments.of(
                        Named.of("5등 1개",
                                Arrays.asList(
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
                                Arrays.asList(
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
