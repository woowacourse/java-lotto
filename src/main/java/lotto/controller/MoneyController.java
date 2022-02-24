package lotto.controller;

import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

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
            OutputView.printError(exception.getMessage());
            return null;
        }
    }
}