package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoResultTest {
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

        Lotto winningNumbers = Lotto.from(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Ball bonusBall = ball7;
        winningLotto = new WinningLotto(winningNumbers, bonusBall);
    }

    @Test
    @DisplayName("수익률 계산")
    void calculate_profit() {
        Lotto lotto1 = Lotto.from(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Lotto lotto2 = Lotto.from(List.of(ball2, ball3, ball4, ball5, ball6, ball7));
        Lotto lotto3 = Lotto.from(List.of(ball3, ball4, ball5, ball6, ball7, ball8));
        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        Lotto winLotto = Lotto.from(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Ball bonusBall = ball7;
        WinningLotto winningLotto = new WinningLotto(winLotto, bonusBall);

        LottoResult lottoResult = new LottoResult();
        winningLotto.match(lottos, lottoResult);

        Payment payment = new Payment(3000);

        assertEquals(lottoResult.calculateRate(payment), 676683.333, 0.01);
    }
}
