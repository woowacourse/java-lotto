package lotto.controller;

import lotto.domain.result.LottoResult;
import lotto.domain.user.Money;
import lotto.domain.user.PurchaseLottoCount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class MoneyController {

    public Money inputMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    public PurchaseLottoCount inputPurchaseLottoCount(final int maxCount) {
        try {
            return new PurchaseLottoCount(InputView.inputPurchaseLottoCount(), maxCount);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPurchaseLottoCount(maxCount);
        }
    }

    public double calculateProfit(final Money money, final LottoResult lottoResult) {
        return (double) lottoResult.calculateWinningMoney() / money.getMoney();
    }

    public void printProfit(final double profit) {
        OutputView.printProfit(profit);
    }
}
