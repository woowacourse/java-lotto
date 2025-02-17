package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @ParameterizedTest
    @MethodSource("allRankInfo")
    void 등수를_판단한다(int matchCount, boolean matchBonus, Rank expected) {
        assertThat(Rank.of(matchCount, matchBonus)).isEqualTo(expected);
    }

    private static Stream<Arguments> allRankInfo() {
        return Stream.of(
                Arguments.arguments(6, false, Rank.FIRST),
                Arguments.arguments(6, true, Rank.FIRST),
                Arguments.arguments(5, true, Rank.SECOND),
                Arguments.arguments(5, false, Rank.THIRD),
                Arguments.arguments(4, true, Rank.FOURTH),
                Arguments.arguments(4, false, Rank.FOURTH),
                Arguments.arguments(3, true, Rank.FIFTH),
                Arguments.arguments(3, false, Rank.FIFTH),
                Arguments.arguments(2, false, Rank.NONE),
                Arguments.arguments(1, false, Rank.NONE),
                Arguments.arguments(0, false, Rank.NONE)
        );
    }
}