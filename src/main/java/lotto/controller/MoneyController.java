package lotto.controller;

import lotto.domain.Money;
import lotto.domain.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class MoneyController {

    public Money inputMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMoney();
        }
    }

    public void printProfit(Result result, Money money) {
        double profit = (double) result.getWinnigMoney() / money.getMoney();
        OutputView.printProfit(profit);
    }
}
