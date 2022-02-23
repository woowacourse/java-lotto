package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeTest {

    @DisplayName("3개가 일치하면 5등을 반환한다")
    @Test
    void match_3_fifth() {
        assertThat(Prize.getPrize(3)).isEqualTo(Prize.FIFTH);
    }

    @DisplayName("4개가 일치하면 4등을 반환한다")
    @Test
    void match_4_fourth() {
        assertThat(Prize.getPrize(4)).isEqualTo(Prize.FOURTH);
    }

    @DisplayName("5개가 일치하면 3등을 반환한다")
    @Test
    void match_5_third() {
        assertThat(Prize.getPrize(5)).isEqualTo(Prize.THIRD);
    }

    @DisplayName("6개가 일치하면 1등을 반환한다")
    @Test
    void match_6_first() {
        assertThat(Prize.getPrize(6)).isEqualTo(Prize.FIRST);
    }
}
