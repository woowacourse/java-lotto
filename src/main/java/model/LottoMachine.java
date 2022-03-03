package model;

import model.lottonumber.Lottos;
import model.money.PurchaseMoney;

public class LottoMachine {

    private Lottos lottos;
    private PurchaseMoney purchaseMoney;

    public void insertPurchaseMoney(int purchaseMoney) {
        this.purchaseMoney = new PurchaseMoney(purchaseMoney);
    }

    public int getPurchaseLottoCount() {
        return purchaseMoney.makePurchaseLottoCount();
    }
}
