package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.Prize.calculate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PrizeTest {
    @DisplayName("존재하는 등수 계산 성공")
    @Test
    void calculate_prize_success() {
        assertAll(
                () -> assertThat(calculate(6, true)).isEqualTo(Prize.FIRST),
                () -> assertThat(calculate(5, true)).isEqualTo(Prize.SECOND),
                () -> assertThat(calculate(5, false)).isEqualTo(Prize.THIRD),
                () -> assertThat(calculate(4, true)).isEqualTo(Prize.FOURTH),
                () -> assertThat(calculate(4, false)).isEqualTo(Prize.FOURTH),
                () -> assertThat(calculate(3, true)).isEqualTo(Prize.FIFTH),
                () -> assertThat(calculate(3, false)).isEqualTo(Prize.FIFTH)
        );
    }

    @DisplayName("당첨되지 않은 경우")
    @Test
    void calculate_nothing_success() {
        assertAll(
                () -> assertThat(calculate(2, true)).isEqualTo(Prize.NOTHING),
                () -> assertThat(calculate(2, false)).isEqualTo(Prize.NOTHING),
                () -> assertThat(calculate(1, true)).isEqualTo(Prize.NOTHING),
                () -> assertThat(calculate(1, false)).isEqualTo(Prize.NOTHING),
                () -> assertThat(calculate(0, true)).isEqualTo(Prize.NOTHING),
                () -> assertThat(calculate(0, false)).isEqualTo(Prize.NOTHING)
        );
    }
}
