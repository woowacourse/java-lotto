package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {
    public void play() {
        Money money = inputPurchaseAmountWithValidation();
        LottoCount lottoCount = inputLottoCountWithValidation(money);

        Lottos lottos = buyManualLottos(lottoCount);
        LottoMachine.buyAutoLottos(lottos, lottoCount);
        OutputView.printLottos(lottoCount, lottos);
        WinningNumber winningNumber = inputWinningNumberWithValidation();

        LottoResult lottoResult = new LottoResult(lottos, winningNumber);
        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(money.calculateProfitRatio(lottoResult));
    }

    private Lottos buyManualLottos(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int index = 0; index < lottoCount.getManualCount(); index++) {
            lottos.add(inputManualNumbersWithValidation());
        }
        return new Lottos(lottos);
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

    private static Lotto inputManualNumbersWithValidation() {
        try {
            LottoGenerator lottoGenerator = new ManualLottoGenerator(InputView.inputManualLottoNumbers());
            return lottoGenerator.generateLotto();
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputManualNumbersWithValidation();
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
