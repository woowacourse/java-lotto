package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @MethodSource("provide")
    @ParameterizedTest
    void findBy(int matchingCount, boolean hasBonus, Rank expected) {
        assertThat(Rank.findBy(matchingCount, hasBonus)).isEqualTo(expected);
    }

    private static Stream<Arguments> provide() {
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
