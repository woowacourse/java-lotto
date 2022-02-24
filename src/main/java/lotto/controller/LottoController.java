package lotto.controller;

import lotto.model.LottoGame;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {
    public static void runGame() {
        Money money = new Money(InputView.insertMoney());
        Lottos lottos = LottoGame.buyLottos(money);
        ResultView.printGeneratedLottos(lottos.getLottos());

        LottoGame lottoGame = new LottoGame(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

        ResultView.printResultStatistics(lottoGame.generateLottoResult(lottos));
        ResultView.printYield(lottoGame.calculateYield(money));
    }
}
