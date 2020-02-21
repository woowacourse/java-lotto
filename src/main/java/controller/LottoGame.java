package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        Money amount = inputPurchaseAmount();
        int lottoCount = amount.getCount();
        OutputView.printPurchaseCountMessage(lottoCount);

        LottoDummy lottoDummy = new LottoDummy(lottoCount);
        OutputView.printLottoDummy(lottoDummy);

        WinningNumber winningNumber = inputWinningNumber();
        LottoResult lottoResult = lottoDummy.countWinningLotto(winningNumber);
        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(Money.calculateProfitRatio(lottoResult));
    }

    private static WinningNumber inputWinningNumber() {
        try {
            return new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch(IllegalArgumentException | NullPointerException e){
            OutputView.printExceptionMessage(e);
        }
        return inputWinningNumber();
    }

    private static Money inputPurchaseAmount() {
        try {
            return new Money(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
        }
        return inputPurchaseAmount();
    }
}
