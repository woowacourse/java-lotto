package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("1등 랭킹")
    @Test
    void getRank_first() {
        Rank rank = Rank.getRank(6, false);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("2등 랭킹")
    @Test
    void getRank_second() {
        Rank rank = Rank.getRank(5, true);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("3등 랭킹")
    @Test
    void getRank_third() {
        Rank rank = Rank.getRank(5, false);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("4등 랭킹")
    @ParameterizedTest
    @CsvSource({"4, false", "4, true"})
    void getRank_fourth(int matchCounts, boolean bonus) {
        Rank rank = Rank.getRank(matchCounts, bonus);

        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("5등 랭킹")
    @ParameterizedTest
    @CsvSource({"3, false", "3, true"})
    void getRank_fifth(int matchCounts, boolean bonus) {
        Rank rank = Rank.getRank(matchCounts, bonus);

        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("NOTHING 랭킹")
    @ParameterizedTest
    @CsvSource({"0, false", "0,true", "1, false", "1, true", "2, false", "2, true"})
    void getRank_nothing(int matchCounts, boolean bonus) {
        Rank rank = Rank.getRank(matchCounts, bonus);

        assertThat(rank).isEqualTo(Rank.NOTHING);
    }
}
