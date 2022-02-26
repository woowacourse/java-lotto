package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import lotto.domain.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @Test
    @DisplayName("매칭한 숫자가 6이면 First를 반환한다.")
    void matchCountToRankFirst() {
        assertThat(Rank.find(6, false)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("매칭한 숫자가 5이면 Third를 반환한다.")
    void matchCountToRankThird() {
        assertThat(Rank.find(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("매칭한 숫자가 6이면 Second를 반환한다.")
    void matchCountToRankSecond() {
        assertThat(Rank.find(5, true)).isEqualTo(Rank.SECOND);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,false", "0,true", "1,false", "1,true", "2,false"})
    @DisplayName("매칭한 숫자가 이면 Second를 반환한다.")
    void matchCountToRankNone(int matchCount, boolean matchBonus) {
        assertThat(Rank.find(matchCount, matchBonus)).isEqualTo(Rank.NONE);
    }

    @ParameterizedTest
    @MethodSource("ranks")
    @DisplayName("당첨 금액의 합을 계산한다.")
    void getFirstReward(List<Rank> ranks) {
        Money money = Rank.calculateReward(ranks);
        long totalReward = ranks.stream().mapToLong(rank -> rank.getReward().getValue()).sum();
        assertThat(money).isEqualTo(new Money(totalReward));
    }

    private static Stream<List<Rank>> ranks() {
        return Stream.of(
                List.of(Rank.FIRST, Rank.FIRST, Rank.FIRST, Rank.NONE),
                List.of(Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH)
        );
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
}
