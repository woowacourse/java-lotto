package lotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @ParameterizedTest
    @MethodSource("createCountAndRank")
    void of(int count, Rank expected) {
        assertThat(Rank.of(count)).isEqualTo(expected);
    }

    private static Stream<Arguments> createCountAndRank() {
        return Stream.of(
                Arguments.of(5, Rank.THIRD),
                Arguments.of(6, Rank.FIRST),
                Arguments.of(3, Rank.FIFTH)
        );
    }

    @Test
    void sumWinningMoney() {
        List<Rank> ranks = Arrays.asList(Rank.FIFTH, Rank.SECOND, Rank.FOURTH);
        double expected = 30055000;
        assertThat(Rank.sumWinningMoney(ranks)).isEqualTo(expected);
    }
}