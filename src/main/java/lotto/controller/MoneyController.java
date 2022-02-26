package lotto.controller;

import lotto.domain.Money;
import lotto.dto.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class MoneyController {

    public Money inputMoney() {
        return new Money(InputView.inputMoney());
    }

    public double calculateProfit(final Result result, final Money money) {
        return (double) result.getWinnigMoney() / money.getMoney();
    }

    public void printProfit(double profit) {
        OutputView.printProfit(profit);
    }
}
