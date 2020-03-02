package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGameApplication {

    public static void main(String[] args) {
        Money money = inputPurchaseAmountWithValidation();
        ManualCount manualCount = inputManualCountWithValidation(money);
        LottoCount lottoCount = new LottoCount(money.getLottoCount(), manualCount.getManualCount());

        Lottos lottos = LottosGenerator.generateTotal(lottoCount);
        OutputView.printLottos(lottoCount, lottos);
        WinningNumber winningNumber = inputWinningNumberWithValidation();
        LottoGame lottoGame = new LottoGame(lottos, winningNumber);

        LottoResult lottoResult = new LottoResult();
        lottoGame.calculateResults(lottoResult);

        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(money.calculateProfitRatio(lottoResult));
    }

    private static Money inputPurchaseAmountWithValidation() {
        try {
            return new Money(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmountWithValidation();
        }
    }

    private static ManualCount inputManualCountWithValidation(final Money money) {
        try {
            return new ManualCount(InputView.inputManualCount(), money.getLottoCount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputManualCountWithValidation(money);
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
