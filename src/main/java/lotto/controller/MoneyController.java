package lotto.controller;

import lotto.domain.Money;

public class MoneyController {
    public Money getMoney(int money) {
        return new Money(money);
    }
}
