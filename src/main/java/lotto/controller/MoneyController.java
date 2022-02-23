package lotto.controller;

import lotto.domain.Money;
import lotto.view.InputView;

public class MoneyController {

    private final Money money;

    public MoneyController() {
        money = new Money(InputView.inputMoney());
    }

    public int getMoney() {
        return money.getMoney();
    }
}
