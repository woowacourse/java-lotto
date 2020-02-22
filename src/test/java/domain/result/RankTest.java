package domain.result;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RankTest {

    @ParameterizedTest
    @MethodSource("createRank")
    void testRank(Rank rank, Rank rankExpected) {
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
    void testRankGetMatches(int counOfMatches, Rank rank) {
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
    void testRankGetWinningMoneyTest(int winningMoney, Rank rank) {
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
    void testRankException() {
        assertThatThrownBy(() -> Rank.valueOf(10, false))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public static List<Rank> getRanksFixture() {
        List<Rank> ranks = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            ranks.add(Rank.FIRST);
        }
        return ranks;
    }

    public static List<Rank> getRanksFromThirdToFifthFixture() {
        List<Rank> ranks = new ArrayList<>();

        ranks.add(Rank.THIRD);
        ranks.add(Rank.FOURTH);
        ranks.add(Rank.FIFTH);

        return ranks;
    }
}
