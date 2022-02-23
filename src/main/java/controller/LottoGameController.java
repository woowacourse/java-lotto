package controller;

import domain.PurchasedLotto;
import domain.strategy.RandomPurchaseStrategy;
import view.InputView;
import view.OutputView;

public class LottoGameController {

    public void start() {
        PurchasedLotto purchasedLotto = new PurchasedLotto(InputView.inputPrice());
        OutputView.printPurchasedLotto(purchasedLotto);
    }
}
