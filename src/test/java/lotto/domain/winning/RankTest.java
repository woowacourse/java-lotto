package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @DisplayName("번호 일치 개수와 보너스 볼 일치 여부에 따른 당첨 등수를 반환해야 한다.")
    @ParameterizedTest(name = "[{index}] 일치 개수 : {0}, 보너스 볼 포함 : {1}, 당첨 등수 : {2}")
    @MethodSource("provideForRankMatchTest")
    void rankMatchTest(final int matchCount, final boolean bonusMatched, final Rank expectedRank) {
        final Rank actualRank = Rank.of(matchCount, bonusMatched).orElse(null);
        assertThat(actualRank).isEqualTo(expectedRank);
    }

    public static Stream<Arguments> provideForRankMatchTest() {
        return Stream.of(
                Arguments.of(6, true, Rank.FIRST_GRADE),
                Arguments.of(6, false, Rank.FIRST_GRADE),

                Arguments.of(5, true, Rank.SECOND_GRADE),
                Arguments.of(5, false, Rank.THIRD_GRADE),

                Arguments.of(4, true, Rank.FOURTH_GRADE),
                Arguments.of(4, false, Rank.FOURTH_GRADE),

                Arguments.of(3, true, Rank.FIFTH_GRADE),
                Arguments.of(3, false, Rank.FIFTH_GRADE),

                Arguments.of(2, true, null),
                Arguments.of(2, false, null),

                Arguments.of(1, true, null),
                Arguments.of(1, false, null),

                Arguments.of(0, true, null),
                Arguments.of(0, false, null)
        );
    }

}
