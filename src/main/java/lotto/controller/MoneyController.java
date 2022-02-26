package lotto.controller;

import java.util.Optional;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class MoneyController {

    public Money getBuyMoney() {
        Optional<Money> money;

        do {
            money = getValidMoney();
        } while (money.isEmpty());
        return money.get();
    }

    private Optional<Money> getValidMoney() {
        try {
            int input = InputView.inputMoney();
            return Optional.of(new Money(input));
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return Optional.empty();
        }
    }
}