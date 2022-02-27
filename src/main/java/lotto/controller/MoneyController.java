package lotto.controller;

import lotto.domain.result.LottoResult;
import lotto.domain.user.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class MoneyController {

    public Money createMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createMoney();
        }
    }

    public double calculateProfit(final Money money, final LottoResult lottoResult) {
        return (double) lottoResult.calculateWinningMoney() / money.getMoney();
    }

    public void printProfit(final double profit) {
        OutputView.printProfit(profit);
    }
}
