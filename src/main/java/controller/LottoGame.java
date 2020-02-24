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
        Lottos lottos = new Lottos();
        createManualLottosWithValidation(lottos, manualCount);
        createAutoLottosWithValidation(lottos, lottoCount, manualCount);

        // 구매 결과
        OutputView.printLottoCountMessage(lottoCount, manualCount);
        OutputView.printLottos(lottos);

        // 당첨 번호 및 보너스 번호 입력
        inputWinningNumbersWithValidation();
        inputBonusNumberWithValidation();

        // 당첨 결과 계산 및 출력
        LottoResult lottoResult = new LottoResult();
        lottoResult.countWinningLotto(lottos);
        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(money.calculateProfitRatio(lottoResult, lottoCount));
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

    private static void createAutoLottosWithValidation(Lottos lottos, LottoCount lottoCount, ManualCount manualCount) {
        try {
            lottos.addLottos(LottosFactory.createAutoLottos(lottoCount.getAutoLottoCount(manualCount)));
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            createAutoLottosWithValidation(lottos, lottoCount, manualCount);
        }
    }

    private static void createManualLottosWithValidation(Lottos lottos, ManualCount manualCount) {
        try {
            lottos.addLottos(LottosFactory.createManualLottos(manualCount));
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            createManualLottosWithValidation(lottos, manualCount);
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
