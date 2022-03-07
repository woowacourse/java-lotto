package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @ParameterizedTest(name = "맞춘 갯수 : {0}, 보너스볼 여부 : {1}, 순위 : {2}")
    @MethodSource("getNumbers")
    @DisplayName("맞춘 번호에 따라 등수를 반환한다.")
    void findRank(int matchCount, boolean matchBonus, Rank rank) {
        assertThat(Rank.find(matchCount, matchBonus)).isEqualTo(rank);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,false", "0,true", "1,false", "1,true", "2,false"})
    @DisplayName("매칭한 숫자가 2개 이하면 None을 반환한다.")
    void matchCountToRankNone(int matchCount, boolean matchBonus) {
        assertThat(Rank.find(matchCount, matchBonus)).isEqualTo(Rank.NONE);
    }

    @Test
    @DisplayName("같은 등수가 몇개 포함되어있는지 개수를 반환한다.")
    void getRewardCount() {
        List<Rank> ranks = List.of(Rank.FIRST, Rank.FIRST, Rank.FIRST, Rank.NONE);

        assertThat(Rank.FIRST.findRewardCount(ranks)).isEqualTo(3);
    }

    private static Stream<Arguments> getNumbers() {
        return Stream.of(
            Arguments.arguments(6, false, Rank.FIRST),
            Arguments.arguments(5, true, Rank.SECOND),
            Arguments.arguments(5, false, Rank.THIRD),
            Arguments.arguments(4, false, Rank.FOURTH),
            Arguments.arguments(3, false, Rank.FIFTH),
            Arguments.arguments(2, false, Rank.NONE),
            Arguments.arguments(1, false, Rank.NONE),
            Arguments.arguments(0, false, Rank.NONE)
        );
    }
}
