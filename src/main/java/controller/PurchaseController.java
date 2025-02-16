package controller;

import model.Purchase;
import view.PurchaseView;

public class PurchaseController {
    PurchaseView purchaseView = new PurchaseView();

    public Purchase purchase() {
        purchaseView.printPurchaseGuide();
        int purchaseAmount = purchaseView.readPurchaseAmount();
        Purchase purchase = new Purchase(purchaseAmount);
        purchaseView.printPurchaseResult(purchase.size());
        purchaseView.printPurchasedLottos(purchase);
        return purchase;
    }
}

