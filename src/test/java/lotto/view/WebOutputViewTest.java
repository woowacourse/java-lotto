package lotto.view;

import lotto.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WebOutputViewTest {
    private Lotto lotto;
    private WinningLotto winningLotto;
    private Lottos lottos;

    @BeforeEach
    void setup() {
        this.lotto = Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(1,2,3,4,5,6)));
        this.winningLotto = new WinningLotto(Arrays.asList(1,2,3,4,5,6), 7);
        this.lottos = new Lottos(Arrays.asList(
                Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(1,2,3,4,5,6))),
                Lotto.create(new ManualLottoNumbersGenerator(Arrays.asList(1,2,3,4,5,7)))
        ));
    }

    @Test
    void outputLottoTest() {
        assertThat(WebOutputView.outputLotto(lotto)).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    void outputWinningLottoTest() {
        assertThat(WebOutputView.outputWinningLotto(winningLotto)).isEqualTo("[1, 2, 3, 4, 5, 6] + 7");
    }

    @Test
    void outputLottosTest() {
        assertThat(WebOutputView.outputLottos(lottos)).isEqualTo(Arrays.asList("[1, 2, 3, 4, 5, 6]", "[1, 2, 3, 4, 5, 7]"));
    }

    @Test
    void outputResult() {
        assertThat(WebOutputView.outputResult(new WinningStatistics(lottos.match(winningLotto))))
                .isEqualTo(Arrays.asList("1등: 1회", "2등: 1회"));
    }
}