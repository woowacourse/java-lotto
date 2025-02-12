package controller;

import model.PurchasedLottos;
import view.PurchaseView;

public class PurchaseController {
    PurchaseView purchaseView = new PurchaseView();

    public PurchasedLottos purchase() {
        purchaseView.printPurchaseGuide();
        Integer purchaseAmount = purchaseView.readPurchaseAmount();
        return new PurchasedLottos(purchaseAmount);
    }
}

