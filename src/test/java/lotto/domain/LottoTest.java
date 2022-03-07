package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {
    Ball ball1, ball2, ball3, ball4, ball5, ball6, ball7;

    @BeforeEach
    void before() {
        ball1 = Ball.of(1);
        ball2 = Ball.of(2);
        ball3 = Ball.of(3);
        ball4 = Ball.of(4);
        ball5 = Ball.of(5);
        ball6 = Ball.of(6);
        ball7 = Ball.of(7);
    }

    @Test
    @DisplayName("로또 생성 시 Ball 객체 6개 생성")
    void ball_count() {
        assertThatThrownBy(() -> {
            Lotto lotto = Lotto.from(List.of(ball1, ball2, ball3));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 생성 시 Ball 숫자 중복")
    void ball_duplicated() {
        assertThatThrownBy(() -> {
            Lotto lotto = Lotto.from(List.of(ball1, ball2, ball2, ball4, ball5, ball6));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스볼 중복")
    void bonus_ball_duplicated() {
        Lotto lotto = Lotto.from(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Ball bonusBall = ball1;

        assertTrue(lotto.contains(bonusBall));
    }
}
