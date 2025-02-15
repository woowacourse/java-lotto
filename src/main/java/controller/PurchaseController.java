package controller;

import model.PurchasedLottos;
import view.InputView;
import view.OutputView;

public class PurchaseController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public PurchasedLottos purchase() {
        Integer purchaseAmount = inputView.readPurchaseAmount();
        PurchasedLottos purchasedLottos = new PurchasedLottos(purchaseAmount);
        outputView.printPurchaseResult(purchasedLottos.size());
        outputView.printPurchasedLottos(purchasedLottos);
        return purchasedLottos;
    }
}

