package lotto.controller;

import lotto.domain.result.LottoResult;
import lotto.domain.user.Money;
import lotto.domain.user.PurchaseLottoCount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class userController {

    public Money inputMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    public PurchaseLottoCount calculatePurchaseLottoCountInfo(final Money money) {
        try {
            return new PurchaseLottoCount(InputView.inputPurchaseLottoCount(), money.getCount());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return calculatePurchaseLottoCountInfo(money);
        }
    }

    public double calculateProfit(final Money money, final LottoResult lottoResult) {
        return (double) lottoResult.calculateWinningMoney() / money.getMoney();
    }

    public void printProfit(final double profit) {
        OutputView.printProfit(profit);
    }
}
