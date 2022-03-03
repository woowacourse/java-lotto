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
        ball1 = new Ball(1);
        ball2 = new Ball(2);
        ball3 = new Ball(3);
        ball4 = new Ball(4);
        ball5 = new Ball(5);
        ball6 = new Ball(6);
        ball7 = new Ball(7);
        ball8 = new Ball(8);

        Lotto winningNumbers = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Ball bonusBall = ball7;
        winningLotto = new WinningLotto(winningNumbers, bonusBall);
    }

    @Test
    @DisplayName("수익률 계산")
    void calculate_profit() {
        Lotto lotto1 = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Lotto lotto2 = new Lotto(List.of(ball2, ball3, ball4, ball5, ball6, ball7));
        Lotto lotto3 = new Lotto(List.of(ball3, ball4, ball5, ball6, ball7, ball8));
        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        Lotto winLotto = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Ball bonusBall = ball7;
        WinningLotto winningLotto = new WinningLotto(winLotto, bonusBall);

        LottoResult lottoResult = new LottoResult();
        winningLotto.match(lottos, lottoResult);

        Payment payment = new Payment(3000);
        int totalMoney = lottoResult.getTotalMoney();

        assertEquals(lottoResult.calculateRate(totalMoney, payment), 676683.333, 0.01);
    }
}
