package lotto.model.prize;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeTest {
    @DisplayName("3개가 일치하면 5등을 반환한다")
    @Test
    void match_3_fifth() {
        assertThat(Prize.getPrize(new MatchResult(3, true))).isEqualTo(Prize.FIFTH);
    }

    @DisplayName("4개가 일치하면 4등을 반환한다")
    @Test
    void match_4_fourth() {
        assertThat(Prize.getPrize(new MatchResult(4, false))).isEqualTo(Prize.FOURTH);
    }

    @DisplayName("5개가 일치하면 3등을 반환한다")
    @Test
    void match_5_third() {
        assertThat(Prize.getPrize(new MatchResult(5, false))).isEqualTo(Prize.THIRD);
    }

    @DisplayName("6개가 일치하면 1등을 반환한다")
    @Test
    void match_6_first() {
        assertThat(Prize.getPrize(new MatchResult(6, false))).isEqualTo(Prize.FIRST);
    }

    @DisplayName("5개가 일치하고 보너스가 일치하면 2등을 반환한다")
    @Test
    void match_5_bonus_second() {
        assertThat(Prize.getPrize(new MatchResult(5, true))).isEqualTo(Prize.SECOND);
    }

    @DisplayName("2개가 일치하면 당첨되지 않는다")
    @Test
    void match_2_none() {
        assertThat(Prize.getPrize(new MatchResult(2, true))).isEqualTo(Prize.NONE);
    }
}
