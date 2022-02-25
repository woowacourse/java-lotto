package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottosTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void before() {
        Lotto winLotto = new Lotto(Arrays.asList("1", "2", "3", "4", "5", "6"));
        Ball bonusBall = new Ball("7");
        winningLotto = new WinningLotto(winLotto, bonusBall);
    }

    @Test
    @DisplayName("로또 수를 입력받은 만큼 로또 생성")
    void create_lottos() {
        Lottos lottos = new Lottos(14);
        assertEquals(lottos.getLottos().size(), 14);
    }
}
