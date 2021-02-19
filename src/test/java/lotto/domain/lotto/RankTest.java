package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @ParameterizedTest
    @DisplayName("Rank 테스트")
    @MethodSource("getInstance_testcase")
    void getInstance(int numberMatchCount, boolean isBonusBallMatches, Rank expectedRank) {
        assertThat(Rank.getInstance(numberMatchCount, isBonusBallMatches)).isEqualTo(expectedRank);
    }

    static Stream<Arguments> getInstance_testcase() {
        return Stream.of(
                Arguments.of(6, false, Rank.FIRST_PLACE),
                Arguments.of(6, true, Rank.FIRST_PLACE),
                Arguments.of(5, true, Rank.SEC0ND_PLACE),
                Arguments.of(5, false, Rank.THIRD_PLACE),
                Arguments.of(4, false, Rank.FOURTH_PLACE),
                Arguments.of(4, true, Rank.FOURTH_PLACE),
                Arguments.of(3, false, Rank.FIFTH_PLACE),
                Arguments.of(3, true, Rank.FIFTH_PLACE),
                Arguments.of(2, false, Rank.UNRANKED),
                Arguments.of(0, false, Rank.UNRANKED)
        );
    }
}