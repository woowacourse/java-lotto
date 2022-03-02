package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoGeneratorTest {
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

    @DisplayName("자동 로또를 생성")
    @Test
    void generate_auto_lottos() {
        Lottos autoLottos = LottoGenerator.pickAutoLottos(new LottoCount(new Payment(5000), 3));

        assertEquals(autoLottos.getLottos().size(), 2);
    }

    @DisplayName("수동 로또를 생성")
    @Test
    void generate_manual_lottos() {
        Lotto lotto1 = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Lotto lotto2 = new Lotto(List.of(ball1, ball2, ball3, ball4, ball5, ball6));
        Lottos manualLottos = LottoGenerator.pickManualLottos(List.of(lotto1, lotto2));

        assertEquals(manualLottos.getLottos().size(), 2);
    }
}