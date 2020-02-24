package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    public static void main(String[] args) {
        Money amount = inputPurchaseAmount();
        int lottoCount = amount.getCount();
        ManualCount manualCount = inputManualCount(lottoCount);

        Lottos lottos = LottosFactory.createAutoLottos(lottoCount - manualCount.getManualCount());
        OutputView.printInputManualLottoNumbers();
        List<Lotto> manualLottos = new ArrayList<>();
        for (int index = 0; index < manualCount.getManualCount(); index++) {
            String[] manualLottoNumbers = inputManualLottoNumbers();
            manualLottos.add(LottoFactory.createOneManualLotto(manualLottoNumbers));
        }
        lottos.addLottos(manualLottos);
        OutputView.printPurchaseCountMessage(manualCount, lottoCount);
        OutputView.printLottos(lottos);

        inputWinningNumbersWithValidation();
        inputBonusNumberWithValidation();
        LottoResult.countWinningLotto(lottos);

        OutputView.printResult();
        OutputView.printProfitRatio(Money.calculateProfitRatio(lottoCount));
    }

    private static Money inputPurchaseAmount() {
        try {
            return new Money(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmount();
        }
    }

    private static String[] inputManualLottoNumbers() {
        return InputView.inputManualLottoNumbers();
    }

    private static ManualCount inputManualCount(int lottoCount) {
        try {
            return new ManualCount(InputView.inputManualCount(), lottoCount);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputManualCount(lottoCount);
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
