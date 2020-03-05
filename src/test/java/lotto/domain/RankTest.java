package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @DisplayName("일치하는 개수에 해당하는 순위를 반환")
    @ParameterizedTest
    @MethodSource("createCountAndRank")
    void returnRank(int count, Rank expected) {
        assertThat(Rank.from(count)).isEqualTo(expected);
    }

    private static Stream<Arguments> createCountAndRank() {
        return Stream.of(
                Arguments.of(5, Rank.THIRD),
                Arguments.of(6, Rank.FIRST),
                Arguments.of(3, Rank.FIFTH),
                Arguments.of(2, Rank.LOSE)
        );
    }

    @DisplayName("해당하는 Rank의 개수를 반환")
    @Test
    void getContainingCount() {
        List<Rank> ranks = Arrays.asList(Rank.FOURTH, Rank.FIFTH, Rank.SECOND, Rank.FOURTH, Rank.FOURTH);

        int actual = Rank.FOURTH.getContainingCount(ranks);
        int expected = 3;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Rank.LOSE를 제외한 Rank들을 반환")
    @Test
    void validValues() {
        List<Rank> validRanks = Rank.winningValues();
        List<Rank> containingRanks = Arrays.asList(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH);

        assertThat(validRanks.containsAll(containingRanks)).isTrue();
        assertThat(validRanks.contains(Rank.LOSE)).isFalse();
    }
}