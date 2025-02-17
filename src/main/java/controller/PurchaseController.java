package controller;

import model.LottoNumberGenerator;
import model.PurchasedLottos;
import view.InputView;
import view.OutputView;

public class PurchaseController {
    private final InputView inputView;
    private final OutputView outputView;

    public PurchaseController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public PurchasedLottos purchase() {
        Integer purchaseAmount = inputView.readPurchaseAmount();
        PurchasedLottos purchasedLottos = new PurchasedLottos(purchaseAmount, new LottoNumberGenerator());
        outputView.printPurchaseResult(purchasedLottos.size());
        outputView.printPurchasedLottos(purchasedLottos);
        return purchasedLottos;
    }
}

