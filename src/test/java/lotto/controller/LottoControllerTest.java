package lotto.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import lotto.model.BonusNumber;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.view.ResultView;
import org.junit.jupiter.api.Test;

public class LottoControllerTest {

    @Test
    void 실행_테스트() {
        assertThatCode(() ->
                run())
                .doesNotThrowAnyException();
    }

    void run() {
        Lottos lottos = new Lottos(new Money(14000));
        ResultView.printResult(lottos);

        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), new BonusNumber(7));

        winningLotto.checkRank(lottos);
        lottos.countRank();
        ResultView.printTotalResult(lottos);
    }
}
