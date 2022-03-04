package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.List;

public class LottoController {
    public static void runGame() {
        Money money = new Money(InputView.insertMoney());
        LottoSize numberOfManualLotto = new LottoSize(InputView.insertNumberOfManualLotto());
        List<Lotto> manualLottos = InputView.insertManualLottos(numberOfManualLotto);
        Lottos lottos = LottoGame.buyLottos(money, manualLottos, numberOfManualLotto);
        ResultView.printBuyingLotto(lottos.getLottos(), numberOfManualLotto.getSize());

        LottoGame lottoGame = new LottoGame(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

        LottoResult lottoResult = lottoGame.generateLottoResult(lottos);
        ResultView.printResultStatistics(lottoResult);
        ResultView.printYield(lottoGame.calculateYield(money, lottoResult));
    }
}
