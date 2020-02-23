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

        WinningNumber winningNumber = inputWinningNumber();
        LottoResult lottoResult = winningNumber.countWinningLotto(lottos);
        OutputView.printResult(lottoResult);
        OutputView.printProfitRatio(Money.calculateProfitRatio(lottoResult, lottoCount));
    }

    private static String[] inputManualLottoNumbers() {
        return InputView.inputManualLottoNumbers();
    }

    private static ManualCount inputManualCount(int lottoCount) {
        try {
            return new ManualCount(InputView.inputManualCount(), lottoCount);
        } catch(IllegalArgumentException e){
            OutputView.printExceptionMessage(e);
            return inputManualCount(lottoCount);
        }
    }

    private static WinningNumber inputWinningNumber() {
        try {
            return new WinningNumber(InputView.inputWinningNumbers(), InputView.inputBonusNumber());
        } catch(IllegalArgumentException | NullPointerException e){
            OutputView.printExceptionMessage(e);
            return inputWinningNumber();
        }
    }

    private static Money inputPurchaseAmount() {
        try {
            return new Money(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmount();
        }
    }
}
