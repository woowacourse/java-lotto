package lotto.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @ParameterizedTest
    @MethodSource("createRank")
    @DisplayName("Rank는 일치하는 번호 수와 보너스 일치 여부를 받아서 Rank 반환")
    void rankValueOf(Rank rank, Rank rankExpected) {
        assertThat(rank).isEqualTo(rankExpected);
    }

    private static Stream<Arguments> createRank() {
        return Stream.of(
                Arguments.of(Rank.valueOf(1, false), Rank.MISS),
                Arguments.of(Rank.valueOf(3, false), Rank.FIFTH),
                Arguments.of(Rank.valueOf(4, false), Rank.FOURTH),
                Arguments.of(Rank.valueOf(5, false), Rank.THIRD),
                Arguments.of(Rank.valueOf(5, true), Rank.SECOND),
                Arguments.of(Rank.valueOf(6, false), Rank.FIRST)
        );
    }

    @ParameterizedTest
    @MethodSource("getRank")
    @DisplayName("각 rank는 몇개가 일치하는지 반환")
    void getCountOfMatches(int counOfMatches, Rank rank) {
        assertThat(rank.getCountOfMatches()).isEqualTo(counOfMatches);
    }

    private static Stream<Arguments> getRank() {
        return Stream.of(
                Arguments.of(0, Rank.MISS),
                Arguments.of(3, Rank.FIFTH),
                Arguments.of(4, Rank.FOURTH),
                Arguments.of(5, Rank.THIRD),
                Arguments.of(5, Rank.SECOND),
                Arguments.of(6, Rank.FIRST)
        );
    }

    @ParameterizedTest
    @MethodSource("getWinningMoney")
    @DisplayName("Rank의 우승 상금을 반환")
    void getWinningMoney(int winningMoney, Rank rank) {
        assertThat(rank.getWinningMoney()).isEqualTo(winningMoney);
    }

    private static Stream<Arguments> getWinningMoney() {
        return Stream.of(
                Arguments.of(0, Rank.MISS),
                Arguments.of(5000, Rank.FIFTH),
                Arguments.of(50000, Rank.FOURTH),
                Arguments.of(1500000, Rank.THIRD),
                Arguments.of(30000000, Rank.SECOND),
                Arguments.of(2000000000, Rank.FIRST)
        );
    }

    @Test
    @DisplayName("rank 반환시 일치하는 개수가 6개를 초과하면 예외 발생")
    void rankValueOfCountOverSixThrowsException() {
        assertThatThrownBy(() -> Rank.valueOf(10, false))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("rank 반환시 일치하는 개수가 0개 미만이면 예외 발생")
    void rankValueOfCountUnderZeroThrowsException() {
        assertThatThrownBy(() -> Rank.valueOf(-1, false))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
