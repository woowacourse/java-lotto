package lotto.model.winning;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @DisplayName("일치개수와 보너스넘버 일치여부에 따라 Rank를 찾아준다.")
    @MethodSource("provideRankCategory")
    @ParameterizedTest
    void findMatchingRank(int matchingCount, boolean hasBonus, Rank expected) {
        assertThat(Rank.findBy(matchingCount, hasBonus)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideRankCategory() {
        return Stream.of(
                Arguments.of(6, false, Rank.FIRST),
                Arguments.of(5, true, Rank.SECOND),
                Arguments.of(5, false, Rank.THIRD),
                Arguments.of(4, true, Rank.FOURTH),
                Arguments.of(3, true, Rank.FIFTH),
                Arguments.of(2, true, Rank.NONE)
        );
    }

}
