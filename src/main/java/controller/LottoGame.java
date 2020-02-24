package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        inputPurchaseAmountWithValidation();
        int lottoCount = Money.getLottoCount();

        inputManualCountWithValidation(lottoCount);
        OutputView.printInputManualLottoNumbersMessage();
        createManualLottosWithValidation();
        createAutoLottosWithValidation(lottoCount);

        // 구매 결과
        OutputView.printPurchaseCountMessage(lottoCount);
        OutputView.printLottos();

        // 당첨 번호 및 보너스 번호 입력
        inputWinningNumbersWithValidation();
        inputBonusNumberWithValidation();

        // 당첨 결과 계산 및 출
        LottoResult.countWinningLotto();
        OutputView.printResult();
        OutputView.printProfitRatio(Money.calculateProfitRatio(lottoCount));
    }

    private static void inputPurchaseAmountWithValidation() {
        try {
            Money.inputPurchaseAmount(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            inputPurchaseAmountWithValidation();
        }
    }

    private static void inputManualCountWithValidation(int lottoCount) {
        try {
            ManualCount.inputManualCount(InputView.inputManualCount(), lottoCount);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            inputManualCountWithValidation(lottoCount);
        }
    }

    private static void createAutoLottosWithValidation(int lottoCount) {
        try {
            Lottos.addLottos(LottosFactory.createAutoLottos(lottoCount - ManualCount.getManualCount()));
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            createAutoLottosWithValidation(lottoCount);
        }
    }

    private static void createManualLottosWithValidation() {
        try {
            Lottos.addLottos(LottosFactory.createManualLottos());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            createManualLottosWithValidation();
        }
    }

    private static void inputWinningNumbersWithValidation() {
        try {
            WinningNumber.inputWinningNumbers(InputView.inputWinningNumbers());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            inputWinningNumbersWithValidation();
        }
    }

    private static void inputBonusNumberWithValidation() {
        try {
            WinningNumber.inputBonusNumber(InputView.inputBonusNumber());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            inputBonusNumberWithValidation();
        }
    }
}
