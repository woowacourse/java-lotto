package lotto.controller;

import lotto.domain.LottoGame;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() {
        Money purchaseMoney = new Money(InputView.requestPurchaseMoney());
        LottoGame lottoGame = new LottoGame();
        lottoGame.purchase(purchaseMoney);
        ResultView.printLottos(lottoGame.getLottos());
    }

}
