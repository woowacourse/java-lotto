package lotto.controller;

import lotto.domain.Buyer;
import lotto.domain.MoneyManager;

public class LottoManager {
    public MoneyManager moneyManager;
    public Buyer buyer;

    public LottoManager(int money) {
        this.moneyManager = new MoneyManager(money);
        this.buyer = new Buyer(moneyManager.purchase());
    }


}
