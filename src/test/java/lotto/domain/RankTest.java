package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.vo.Money;
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

    @Test
    @DisplayName("1등에 3번 당첨되면 60억을 반환한다.")
    void getFirstReward() {
        List<Rank> ranks = List.of(Rank.FIRST, Rank.FIRST, Rank.FIRST, Rank.NONE);
        Money money = Rank.calculateReward(ranks);

        assertThat(money).isEqualTo(new Money(6_000_000_000L));
    }

    @Test
    @DisplayName("2등부터 5등까지 당첨되면 3155만 5천원을 반환한다.")
    void getOtherReward() {
        List<Rank> ranks = List.of(Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH);
        Money money = Rank.calculateReward(ranks);

        assertThat(money).isEqualTo(new Money(31_555_000L));
    }

    @Test
    @DisplayName("로또를 살 수 있는 최대 갯수로 1등에 모두 당첨될 경우 2000억을 반환한다.")
    void getMaxReward() {
        List<Rank> ranks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ranks.add(Rank.FIRST);
        }
        Money money = Rank.calculateReward(ranks);

        assertThat(money).isEqualTo(new Money(2_000_000_000L * 100L));
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
