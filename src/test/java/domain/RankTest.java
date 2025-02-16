package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @ParameterizedTest
    @MethodSource("testMatchRank")
    void 일치개수_보너스일치여부_순위_매치_정상진행_테스트(int matchCount, boolean isBonusMatched, Rank rank) {
        assertThat(Rank.matchRank(matchCount, isBonusMatched)).isEqualTo(rank);
    }

    private static Stream<Arguments> testMatchRank() {
        return Stream.of(
                Arguments.of(6, false, Rank.FIRST),
                Arguments.of(5, true, Rank.SECOND),
                Arguments.of(5, false, Rank.THIRD),
                Arguments.of(4, false, Rank.FOURTH),
                Arguments.of(3, false, Rank.FIFTH)
        );
    }

    @ParameterizedTest
    @MethodSource("testCalculatePrize")
    void 상품_금액_계산_테스트(Rank rank, int count, long expectedTotalPrize) {
        assertThat(Rank.calculateTotalPrize(rank, count)).isEqualTo(expectedTotalPrize);
    }

    private static Stream<Arguments> testCalculatePrize() {
        return Stream.of(
                Arguments.of(Rank.FIFTH, 2, 5000 * 2),
                Arguments.of(Rank.FOURTH, 1, 50000),
                Arguments.of(Rank.THIRD, 0, 0),
                Arguments.of(Rank.SECOND, 2, 60000000),
                Arguments.of(Rank.FIRST, 1, 2000000000)
        );
    }
}
