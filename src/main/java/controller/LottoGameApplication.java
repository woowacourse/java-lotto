package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGameApplication {

    public static void main(String[] args) {
        Money money = inputPurchaseAmountWithValidation();
        LottoCount lottoCount = inputLottoCountWithValidation(money);

        Lottos lottos = new Lottos(lottoCount);
        OutputView.printLottos(lottoCount, lottos);
        WinningNumber winningNumber = inputWinningNumberWithValidation();
        LottoGame lottoGame = new LottoGame(lottos, winningNumber);

        LottoResult lottoResult = lottoGame.calculateResults();
        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(money.calculateProfitRatio(lottoResult));
    }

    private static LottoCount inputLottoCountWithValidation(Money money) {
        try {
            return new LottoCount(money.getLottoCount(), InputView.inputManualCount());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            return inputLottoCountWithValidation(money);
        }
    }

    private static Money inputPurchaseAmountWithValidation() {
        try {
            return new Money(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmountWithValidation();
        }
    }

    private static WinningNumber inputWinningNumberWithValidation() {
        try {
            return new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            return inputWinningNumberWithValidation();
        }
    }

}
