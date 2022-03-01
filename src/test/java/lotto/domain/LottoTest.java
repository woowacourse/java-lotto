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
        ball1 = new Ball(1);
        ball2 = new Ball(2);
        ball3 = new Ball(3);
        ball4 = new Ball(4);
        ball5 = new Ball(5);
        ball6 = new Ball(6);
        ball7 = new Ball(7);
    }

    @Test
    @DisplayName("로또 생성 시 Ball 객체 6개 생성")
    void ball_count() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(List.of(ball1, ball2, ball3));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 생성 시 Ball 숫자 중복")
    void ball_duplicated() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(List.of(ball1, ball2, ball2, ball4, ball5, ball6));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스볼 중복")
    void bonus_ball_duplicated() {
        Lotto lotto = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Ball bonusBall = ball1;

        assertTrue(lotto.contains(bonusBall));
    }

    @Test
    @DisplayName("로또의 숫자가 모두 일치할 때")
    void calc_same_count() {
        Lotto lotto = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Lotto winLotto = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));

        int matchingCount = lotto.getMatchingCount(winLotto);

        assertEquals(matchingCount, 6);
    }

    @Test
    @DisplayName("로또의 숫자가 일치하지 않을 때")
    void calc_same_count_different() {
        Lotto lotto = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball7));
        Lotto winLotto = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));

        int matchingCount = lotto.getMatchingCount(winLotto);

        assertNotEquals(matchingCount, 6);
    }

}
