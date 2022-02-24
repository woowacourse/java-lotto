package lotto.controller;

import lotto.model.LottoGame;
import lotto.model.LottoResult;
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

        LottoResult lottoResult = lottoGame.generateLottoResult(lottos);
        ResultView.printResultStatistics(lottoResult);
        ResultView.printYield(lottoGame.calculateYield(money, lottoResult));
    }
}
