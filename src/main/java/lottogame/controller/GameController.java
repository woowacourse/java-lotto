package lottogame.controller;

import lottogame.view.InputView;
import lottogame.view.OutputView;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoGenerator;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.Money;
import lottogame.domain.lotto.WinningLotto;
import lottogame.domain.stats.LottoResults;

import java.util.List;

public class GameController {
    private Money money;
    private Lottos lottos;

    public GameController() {
        LottoGenerator.generate();
    }

    public void run() {
        inputMoney();
        buyLotto();
        OutputView.printResult(matchLottos());
    }

    private LottoResults matchLottos() {
        return lottos.findMatchLottos(inputWinningLotto(), money);
    }

    private WinningLotto inputWinningLotto() {
        String numbers = InputView.inputWinningLottoNumbers();
        String bonusNumber = InputView.inputBonusNumber();
        return new WinningLotto(numbers, bonusNumber);
    }

    private void buyLotto() {
        int quantity = money.lottoQuantity();
        List<Lotto> lottoGroup = LottoGenerator.makeLottos(quantity);
        lottos = new Lottos(lottoGroup);
        printPurchasedLottos(quantity);
    }

    private void printPurchasedLottos(int quantity) {
        OutputView.showLottoQuantity(quantity);
        OutputView.showLottos(lottos.values());
    }

    private void inputMoney() {
        money = new Money(InputView.inputMoney());
    }
}
