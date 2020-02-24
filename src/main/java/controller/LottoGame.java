package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        // 구매 금액
        inputPurchaseAmountWithValidation();

        // 수동 구매 개수
        inputManualCountWithValidation();

        // 수동 및 자동 로또 번호 입력
        OutputView.printInputManualLottoNumbersMessage();
        createManualLottosWithValidation();
        createAutoLottosWithValidation();

        // 구매 결과
        OutputView.printPurchaseCountMessage();
        OutputView.printLottos();

        // 당첨 번호 및 보너스 번호 입력
        inputWinningNumbersWithValidation();
        inputBonusNumberWithValidation();

        // 당첨 결과 계산 및 출력
        LottoResult.countWinningLotto();
        OutputView.printResult();
        OutputView.printProfitRatio(Money.calculateProfitRatio());
    }

    private static void inputPurchaseAmountWithValidation() {
        try {
            Money.inputPurchaseAmount(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            inputPurchaseAmountWithValidation();
        }
    }

    private static void inputManualCountWithValidation() {
        try {
            ManualCount.inputManualCount(InputView.inputManualCount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            inputManualCountWithValidation();
        }
    }

    private static void createAutoLottosWithValidation() {
        try {
            Lottos.addLottos(LottosFactory.createAutoLottos(LottoCount.getAutoLottoCount()));
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            createAutoLottosWithValidation();
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
