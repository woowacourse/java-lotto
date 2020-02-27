package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGameApplication {

    public static void main(String[] args) {
        // 구매 금액
        Money money = inputPurchaseAmountWithValidation();
        LottoCount lottoCount = new LottoCount(money.getLottoCount());

        // 수동 구매 및 로또 번들 생성
        ManualCount manualCount = inputManualCountWithValidation(lottoCount);
        LottoBundle lottoBundle = createLottoBundleWithValidation(lottoCount, manualCount);

        // 구매 결과
        OutputView.printLottoCountMessage(lottoCount, manualCount);
        OutputView.printLottoBundle(lottoBundle);

        // 당첨 번호 및 보너스 번호 입력
        WinningNumber winningNumber = inputWinningNumberWithValidation();

        // 당첨 결과 계산
        LottoResult lottoResult = new LottoResult();
        lottoResult.countWinningLotto(lottoBundle, winningNumber);

        // 결과 및 수익률 출력
        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(money.calculateProfitRatio(lottoResult, lottoCount));
    }

    private static WinningNumber inputWinningNumberWithValidation() {
        try {
            return new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            return inputWinningNumberWithValidation();
        }
    }

    private static LottoBundle createLottoBundleWithValidation(LottoCount lottoCount, ManualCount manualCount) {
        try {
            return LottoBundleFactory.generate(lottoCount, manualCount, InputView.inputManualLottoNumbers(manualCount.getManualCount()));
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            return createLottoBundleWithValidation(lottoCount, manualCount);
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

    private static ManualCount inputManualCountWithValidation(final LottoCount lottoCount) {
        try {
            return new ManualCount(InputView.inputManualCount(), lottoCount);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputManualCountWithValidation(lottoCount);
        }
    }

}
