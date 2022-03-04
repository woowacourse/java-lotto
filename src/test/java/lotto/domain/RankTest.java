package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Argument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @ParameterizedTest
    @MethodSource("matchRanks")
    @DisplayName("일치하는 당첨번호 개수와 보너스볼 여부로 등수를 반환한다.")
    void matchCountAndBonusBall(int matchCount, boolean bonusBall, Rank rank) {
        assertThat(Rank.find(matchCount, bonusBall)).isEqualTo(rank);
    }

    private static Stream<Arguments> matchRanks() {
        return Stream.of(
                Arguments.of(6, false, Rank.FIRST),
                Arguments.of(5, true, Rank.SECOND),
                Arguments.of(5, false, Rank.THIRD),
                Arguments.of(4, false, Rank.FOURTH),
                Arguments.of(3, false, Rank.FIFTH),
                Arguments.of(2, false, Rank.NONE),
                Arguments.of(7, false, Rank.ERROR)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"0,false", "0,true", "1,false", "1,true", "2,false", "2,true"})
    @DisplayName("매칭한 숫자가 2개 이하이면 None을 반환한다.")
    void matchCountToRankNone(int matchCount, boolean matchBonus) {
        assertThat(Rank.find(matchCount, matchBonus)).isEqualTo(Rank.NONE);
    }

    @ParameterizedTest
    @MethodSource("ranks")
    @DisplayName("당첨 금액의 합을 계산한다.")
    void getFirstReward(List<Rank> ranks) {
        Reward reward = Rank.calculateReward(ranks);
        long totalReward = ranks.stream().mapToLong(rank -> rank.getReward().getValue()).sum();
        assertThat(reward).isEqualTo(new Reward(totalReward));
    }

    private static Stream<List<Rank>> ranks() {
        return Stream.of(
                List.of(Rank.FIRST, Rank.FIRST, Rank.FIRST, Rank.NONE),
                List.of(Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH)
        );
    }

    @Test
    @DisplayName("로또를 살 수 있는 최대 갯수로 1등에 모두 당첨될 경우 해당 금액을 반환한다.")
    void getMaxReward() {
        List<Rank> ranks = new ArrayList<>();
        int maxCount = 100;
        for (int i = 0; i < maxCount; i++) {
            ranks.add(Rank.FIRST);
        }
        Reward reward = Rank.calculateReward(ranks);

        assertThat(reward).isEqualTo(new Reward(Rank.FIRST.getReward().getValue() * maxCount));
    }
}
