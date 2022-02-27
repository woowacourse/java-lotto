package lotto.controller;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.number.BonusNumber;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import lotto.view.ResultView;
import org.junit.jupiter.api.Test;

public class LottoControllerTest {

    private LottoNumbers makeLottoNumbers(int[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int num : numbers) {
            lottoNumbers.add(new LottoNumber(num));
        }
        return new LottoNumbers(lottoNumbers);
    }

    @Test
    void 실행_테스트() {
        assertThatCode(this::run)
                .doesNotThrowAnyException();
    }

    void run() {
        Lottos lottos = new Lottos(new Money(14000));
        ResultView.printBuyingLottosResult(lottos);

        LottoNumbers winningLottoNumbers = makeLottoNumbers(new int[]{1, 2, 3, 4, 5, 6});
        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, new BonusNumber(7));
        winningLotto.checkRank(lottos);
        lottos.countRank();
        ResultView.printTotalResult(lottos);
    }
}
