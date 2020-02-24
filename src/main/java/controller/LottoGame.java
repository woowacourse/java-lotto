package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGame {

    public static void main(String[] args) {
        // 구매 금액
        Money money = inputPurchaseAmountWithValidation();
        LottoCount lottoCount = new LottoCount(money.getLottoCount());

        // 수동 구매 개수
        ManualCount manualCount = inputManualCountWithValidation(lottoCount);

        // 수동 및 자동 로또 번호 입력
        OutputView.printInputManualLottoNumbersMessage();
        createManualLottosWithValidation(manualCount);
        createAutoLottosWithValidation(lottoCount, manualCount);

        // 구매 결과
        OutputView.printPurchaseCountMessage(lottoCount, manualCount);
        OutputView.printLottos();

        // 당첨 번호 및 보너스 번호 입력
        inputWinningNumbersWithValidation();
        inputBonusNumberWithValidation();

        // 당첨 결과 계산 및 출력
        LottoResult.countWinningLotto();
        OutputView.printResult();
        OutputView.printProfitRatio(money.calculateProfitRatio(lottoCount));
    }

    private static Money inputPurchaseAmountWithValidation() {
        try {
            return new Money(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmountWithValidation();
        }
    }

    private static ManualCount inputManualCountWithValidation(LottoCount lottoCount) {
        try {
            return new ManualCount(InputView.inputManualCount(), lottoCount);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputManualCountWithValidation(lottoCount);
        }
    }

    private static void createAutoLottosWithValidation(LottoCount lottoCount, ManualCount manualCount) {
        try {
            Lottos.addLottos(LottosFactory.createAutoLottos(lottoCount.getAutoLottoCount(manualCount)));
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            createAutoLottosWithValidation(lottoCount, manualCount);
        }
    }

    private static void createManualLottosWithValidation(ManualCount manualCount) {
        try {
            Lottos.addLottos(LottosFactory.createManualLottos(manualCount));
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            createManualLottosWithValidation(manualCount);
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
