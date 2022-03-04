package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankingTest {

    @ParameterizedTest
    @MethodSource("parameterProvider")
    void findRanking(int count, boolean isBonus, Ranking expect) {
        // given

        // when
        Ranking ranking = Ranking.findRanking(count, isBonus);

        // then
        assertThat(ranking).isEqualTo(expect);
    }

    private static Stream<Arguments> parameterProvider() {
        return Stream.of(
                Arguments.arguments(6, false, Ranking.FIRST),
                Arguments.arguments(5, true, Ranking.SECOND),
                Arguments.arguments(5, false, Ranking.THIRD),
                Arguments.arguments(4, false, Ranking.FOURTH),
                Arguments.arguments(3, false, Ranking.FIFTH),
                Arguments.arguments(2, false, Ranking.NONE),
                Arguments.arguments(0, false, Ranking.NONE)
        );
    }
}