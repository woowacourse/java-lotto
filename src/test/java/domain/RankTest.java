package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    public static Stream<Arguments> getRankTestData() {
        return Stream.of(Arguments.of(3, false, Rank.THREE),
                Arguments.of(4, true, Rank.FOUR),
                Arguments.of(5, false, Rank.FIVE),
                Arguments.of(5, true, Rank.FIVE_WITH_BONUS),
                Arguments.of(6, false, Rank.SIX),
                Arguments.of(2, true, Rank.NONE));

    }

    @MethodSource("getRankTestData")
    @ParameterizedTest
    void 맞은_로또_개수와_보너스_여부에_따라_등수를_반환한다(int matchCount, boolean matchBonus, Rank expectedRank) {
        assertThat(Rank.findByMatchCountAndBonus(matchCount, matchBonus)).isEqualTo(expectedRank);
    }

}