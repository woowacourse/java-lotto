package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {
    Ball ball1, ball2, ball3, ball4, ball5, ball6;

    @BeforeEach
    void before() {
        ball1 = new Ball(1);
        ball2 = new Ball(2);
        ball3 = new Ball(3);
        ball4 = new Ball(4);
        ball5 = new Ball(5);
        ball6 = new Ball(6);
    }

    @Test
    @DisplayName("당첨번호에 null 값을 입력할 경우")
    void winning_numbers_null() {
        assertThatThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(null, ball1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 볼에 null 값을 입력할 경우")
    void bonus_ball_null() {
        assertThatThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6)), null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 볼이 지난 주 당첨번호와 중복될 경우")
    void duplicate_bonus_ball() {
        assertThatThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6)), ball1);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}