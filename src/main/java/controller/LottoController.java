package controller;

import domain.LottoGame;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        int money = InputView.requestUserMoney();
        int count = money/1000;
        LottoGame lottoGame = LottoGame.create(count);
        OutputView.printPurchaseInfo(lottoGame.getLottos());
    }
}
