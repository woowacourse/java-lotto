package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @ParameterizedTest
    @MethodSource("lottoResult")
    void matchRank(int matchCount, boolean isMatchBonus, Rank rank) {
        assertThat(Rank.matchRank(matchCount, isMatchBonus)).isEqualTo(rank);
    }

    private static Stream<Arguments> lottoResult() {
        return Stream.of(
                Arguments.of(6, false, Rank.FIRST),
                Arguments.of(6, true, Rank.FIRST),
                Arguments.of(5, false, Rank.THIRD),
                Arguments.of(5, true, Rank.SECOND),
                Arguments.of(4, false, Rank.FOURTH),
                Arguments.of(4, true, Rank.FOURTH),
                Arguments.of(3, false, Rank.FIFTH),
                Arguments.of(3, true, Rank.FIFTH),
                Arguments.of(2, false, Rank.NONE),
                Arguments.of(2, true, Rank.NONE),
                Arguments.of(1, false, Rank.NONE),
                Arguments.of(1, true, Rank.NONE),
                Arguments.of(0, false, Rank.NONE),
                Arguments.of(0, true, Rank.NONE)
        );
    }
}