package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        Money amount = inputPurchaseAmount();
        int lottoCount = amount.getCount();
        OutputView.printPurchaseCountMessage(lottoCount);

        Lottos lottos = new Lottos(lottoCount);
        OutputView.printLottos(lottos);

        WinningNumber winningNumber = inputWinningNumber();
        LottoResult lottoResult = winningNumber.countWinningLotto(lottos);
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
