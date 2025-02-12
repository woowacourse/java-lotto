package controller;

import view.PurchaseView;

public class PurchaseController {
    PurchaseView purchaseView = new PurchaseView();

    public void printPurchaseGuide(){
        purchaseView.printPurchaseGuide();
    }

    public Integer readPurchaseAmount() {
        return purchaseView.readPurchaseAmount();
    }
}

