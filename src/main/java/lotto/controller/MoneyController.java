package lotto.controller;

import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class MoneyController {

    public Money getBuyMoney() {
        Money money;

        do {
            money = getValidMoney(InputView.inputMoney());
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