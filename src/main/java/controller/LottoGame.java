package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGame {
    public void play() {
        Money money = inputPurchaseAmountWithValidation();
        LottoCount lottoCount = inputLottoCountWithValidation(money);

        Lottos lottos = LottoMachine.createLottos(lottoCount);
        OutputView.printLottos(lottoCount, lottos);
        WinningNumber winningNumber = inputWinningNumberWithValidation();

        LottoResult lottoResult = new LottoResult(lottos, winningNumber);
        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(money.calculateProfitRatio(lottoResult));
    }

    private static LottoCount inputLottoCountWithValidation(Money money) {
        try {
            return new LottoCount(money.getLottoCount(), InputView.inputManualCount());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputLottoCountWithValidation(money);
        }
    }

    private static Money inputPurchaseAmountWithValidation() {
        try {
            return new Money(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputPurchaseAmountWithValidation();
        }
    }

    private static WinningNumber inputWinningNumberWithValidation() {
        try {
            return new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputWinningNumberWithValidation();
        }
    }
}
