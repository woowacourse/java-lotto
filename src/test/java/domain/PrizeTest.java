package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.Prize.choice;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PrizeTest {
    @DisplayName("존재하는 등수 계산 성공")
    @Test
    void choice_prize_success() {
        assertAll(
                () -> assertThat(choice(6, true)).isEqualTo(Prize.FIRST),
                () -> assertThat(choice(5, true)).isEqualTo(Prize.SECOND),
                () -> assertThat(choice(5, false)).isEqualTo(Prize.THIRD),
                () -> assertThat(choice(4, true)).isEqualTo(Prize.FOURTH),
                () -> assertThat(choice(4, false)).isEqualTo(Prize.FOURTH),
                () -> assertThat(choice(3, true)).isEqualTo(Prize.FIFTH),
                () -> assertThat(choice(3, false)).isEqualTo(Prize.FIFTH)
        );
    }

    @DisplayName("당첨되지 않은 경우")
    @Test
    void choice_nothing_success() {
        assertAll(
                () -> assertThat(choice(2, true)).isEqualTo(Prize.NOTHING),
                () -> assertThat(choice(2, false)).isEqualTo(Prize.NOTHING),
                () -> assertThat(choice(1, true)).isEqualTo(Prize.NOTHING),
                () -> assertThat(choice(1, false)).isEqualTo(Prize.NOTHING),
                () -> assertThat(choice(0, true)).isEqualTo(Prize.NOTHING),
                () -> assertThat(choice(0, false)).isEqualTo(Prize.NOTHING)
        );
    }
}
