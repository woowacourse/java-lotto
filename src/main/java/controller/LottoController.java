package controller;

import view.PurchaseMoneyInputView;

public class LottoController {

    Integer getPurchaseMoney() {
        return (new PurchaseMoneyInputView()).printInputPurchaseMoneyAndGet();
    }


}
