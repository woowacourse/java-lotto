package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @DisplayName("1등의 상금을 계산할 수 있다.")
    @Test
    void calculateFirstRankMoney() {
        final Rank first = Rank.FIRST;
        final long count = 3;

        assertThat(Rank.calculateMoney(first, count)).isEqualTo(6000000000L);
    }

    @DisplayName("2등의 상금을 계산할 수 있다.")
    @Test
    void calculateSecondRankMoney() {
        final Rank second = Rank.SECOND;
        final long count = 2;

        assertThat(Rank.calculateMoney(second, count)).isEqualTo(60000000L);
    }

    @DisplayName("3등의 상금을 계산할 수 있다.")
    @Test
    void calculateThirdRankMoney() {
        final Rank third = Rank.THIRD;
        final long count = 4;

        assertThat(Rank.calculateMoney(third, count)).isEqualTo(6000000L);
    }

    @DisplayName("4등의 상금을 계산할 수 있다.")
    @Test
    void calculateFourthRankMoney() {
        final Rank fourth = Rank.FOURTH;
        final long count = 2;

        assertThat(Rank.calculateMoney(fourth, count)).isEqualTo(100000);
    }

    @DisplayName("5등의 상금을 계산할 수 있다.")
    @Test
    void calculateFifthRankMoney() {
        final Rank fifth = Rank.FIFTH;
        final long count = 2;

        assertThat(Rank.calculateMoney(fifth, count)).isEqualTo(10000);
    }
}
