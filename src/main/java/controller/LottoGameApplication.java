package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGameApplication {

    public static final int START_INDEX = 0;

    public static void main(String[] args) {
        // 구매 금액
        Money money = inputPurchaseAmountWithValidation();
        LottoCount lottoCount = new LottoCount(money.getLottoCount());

        // 수동 구매 개수
        ManualCount manualCount = inputManualCountWithValidation(lottoCount);

        // 수동 및 자동 로또 번호 입력
        OutputView.printInputManualLottoNumbersMessage();
        LottoBundle lottoBundle = new LottoBundle();
        createManualLottoBundleWithValidation(lottoBundle, manualCount);
        createAutoLottoBundleWithValidation(lottoBundle, lottoCount, manualCount);

        // 구매 결과
        OutputView.printLottoCountMessage(lottoCount, manualCount);
        OutputView.printLottoBundle(lottoBundle);

        // 당첨 번호 및 보너스 번호 입력
        WinningNumber winningNumber = new WinningNumber();
        inputWinningNumbersWithValidation(winningNumber);
        inputBonusNumberWithValidation(winningNumber);

        // 당첨 결과 계산
        LottoResult lottoResult = new LottoResult();
        lottoResult.countWinningLotto(lottoBundle, winningNumber);

        // 결과 및 수익률 출력
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

    private static ManualCount inputManualCountWithValidation(final LottoCount lottoCount) {
        try {
            return new ManualCount(InputView.inputManualCount(), lottoCount);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputManualCountWithValidation(lottoCount);
        }
    }

    private static void createAutoLottoBundleWithValidation(final LottoBundle lottoBundle, final LottoCount lottoCount, final ManualCount manualCount) {
        try {
            lottoBundle.addLottoBundle(LottoBundleFactory.createAutoLottoBundle(lottoCount.getAutoLottoCount(manualCount)));
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            createAutoLottoBundleWithValidation(lottoBundle, lottoCount, manualCount);
        }
    }

    private static void createManualLottoBundleWithValidation(final LottoBundle lottoBundle, final ManualCount manualCount) {
        for (int index = START_INDEX; index < manualCount.getManualCount(); index++) {
            addOneManualLottoToLottoBundle(lottoBundle);
        }
    }

    private static void addOneManualLottoToLottoBundle(LottoBundle lottoBundle) {
        try {
            lottoBundle.addLotto(LottoFactory
                    .createOneManualLotto(InputView.inputManualLottoNumbers()));
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            addOneManualLottoToLottoBundle(lottoBundle);
        }
    }

    private static void inputWinningNumbersWithValidation(final WinningNumber winningNumber) {
        try {
            winningNumber.inputWinningNumbers(InputView.inputWinningNumbers());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            inputWinningNumbersWithValidation(winningNumber);
        }
    }

    private static void inputBonusNumberWithValidation(final WinningNumber winningNumber) {
        try {
            winningNumber.inputBonusNumber(InputView.inputBonusNumber());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            inputBonusNumberWithValidation(winningNumber);
        }
    }
}
