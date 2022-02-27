package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("당첨번호에 null 값을 입력할 경우")
    void winning_numbers_null() {
        assertThatThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(null, new Ball(1));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 볼에 null 값을 입력할 경우")
    void bonus_ball_null() {
        assertThatThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 볼이 지난 주 당첨번호와 중복될 경우")
    void duplicate_bonus_ball() {
        assertThatThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Ball(1));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}