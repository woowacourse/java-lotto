package lotto.controller;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Arrays;
import java.util.List;
import lotto.model.Money;
import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.model.number.BonusNumber;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import lotto.view.ResultView;
import org.junit.jupiter.api.Test;

public class LottoControllerTest {

    @Test
    void 실행_테스트() {
        assertThatCode(this::run)
                .doesNotThrowAnyException();
    }

    void run() {
        Lottos lottos = new Lottos(new Money(14000));
        ResultView.printBuyingLottosResult(lottos);

        List<LottoNumber> winningLottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        WinningLotto winningLotto = new WinningLotto(new LottoNumbers(winningLottoNumbers), new BonusNumber(7));
        winningLotto.checkRank(lottos);
        lottos.countRank();
        ResultView.printTotalResult(lottos);
    }
}
