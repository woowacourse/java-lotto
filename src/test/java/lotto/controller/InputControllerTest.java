package lotto.controller;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.model.Lottos;
import lotto.model.WinningLotto;
import org.junit.jupiter.api.Test;

public class InputControllerTest {

    private final InputController inputController = new InputController();

    @Test
    void 로또_만들기_테스트() {
        String price = "10000";
        Lottos lottos = inputController.makeLottos(price);
        assertThat(lottos).isInstanceOf(Lottos.class);
    }

    @Test
    void 당첨로_만들기_테스트() {
        String winningNumbers = "1,2,3,4,5,6";
        String bonusNumber = "7";
        WinningLotto winningLotto = inputController.makeWinningLotto(winningNumbers, bonusNumber);
        assertThat(winningLotto).isInstanceOf(WinningLotto.class);
    }
}
