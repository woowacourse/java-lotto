package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.util.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() {
        Money money = inputMoney();
        Lottos lottos = createLottos(money.purchase(Lotto.LOTTO_PRICE));
        ResultView.printResult(lottos);

        WinningLotto winningLotto = createWinningLotto();

        ResultView.printTotalResult(lottos.checkRank(winningLotto));
    }

    private Money inputMoney() {
        try {
            return new Money(InputView.inputPurchaseAmount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputMoney();
        }
    }

    private Lottos createLottos(int lottoCount) {
        return new Lottos(createLotto(lottoCount));
    }

    private List<Lotto> createLotto(int lottoCount) {
        return IntStream.range(0, lottoCount)
            .mapToObj(i -> RandomLottoGenerator.generate())
            .collect(Collectors.toList());
    }

    private WinningLotto createWinningLotto() {
        try {
            return new WinningLotto(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createWinningLotto();
        }
    }
}
