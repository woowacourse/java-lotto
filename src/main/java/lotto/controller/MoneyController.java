package lotto.controller;

import lotto.domain.Money;
import lotto.view.InputView;

public class MoneyController {

    public Money getBuyMoney() {
        String input;
        Money money;

        do {
            input = InputView.inputMoney();
            money = getValidMoney(input);
        } while (money == null);
        return money;
    }

    private Money getValidMoney(String input) {
        try {
            return new Money(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}