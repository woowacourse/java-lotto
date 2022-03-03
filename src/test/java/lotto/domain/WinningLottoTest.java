package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WinningLottoTest {
    Ball ball1, ball2, ball3, ball4, ball5, ball6, ball7, ball8;
    WinningLotto winningLotto;

    @BeforeEach
    void before() {
        ball1 = Ball.of(1);
        ball2 = Ball.of(2);
        ball3 = Ball.of(3);
        ball4 = Ball.of(4);
        ball5 = Ball.of(5);
        ball6 = Ball.of(6);
        ball7 = Ball.of(7);
        ball8 = Ball.of(8);

        Lotto winningNumbers = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Ball bonusBall = ball7;
        winningLotto = new WinningLotto(winningNumbers, bonusBall);
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

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 1등")
    void match_lottos_first() {
        Lotto lotto = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Lottos lottos = new Lottos(List.of(lotto));

        LottoResult lottoResult = new LottoResult();

        winningLotto.match(lottos, lottoResult);

        assertEquals(lottoResult.getLottoResult().get(Rank.FIRST), 1);
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 2등")
    void match_lottos_second() {
        Lotto lotto = new Lotto(List.of(ball2, ball3, ball4, ball5, ball6, ball7));
        Lottos lottos = new Lottos(List.of(lotto));

        LottoResult lottoResult = new LottoResult();

        winningLotto.match(lottos, lottoResult);

        assertEquals(lottoResult.getLottoResult().get(Rank.SECOND), 1);
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 4등")
    void match_lottos_fourth() {
        Lotto lotto = new Lotto(List.of(ball3, ball4, ball5, ball6, ball7, ball8));
        Lottos lottos = new Lottos(List.of(lotto));

        LottoResult lottoResult = new LottoResult();

        winningLotto.match(lottos, lottoResult);

        assertEquals(lottoResult.getLottoResult().get(Rank.FOURTH), 1);
    }
}