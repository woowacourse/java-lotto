package Lotto;

import Lotto.domain.*;
import Lotto.utils.NumberParser;
import Lotto.views.InputView;
import Lotto.views.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(InputView.inputAsPurchaseAmount());

        LottoCount totalLottoCount = new LottoCount(purchaseAmount.calculateLottoAmount());
        LottoCount manualLottoCount = new LottoCount(InputView.inputAsManualLottoCount());
        LottoCount autoLottoCount = new LottoCount(totalLottoCount.getLottoCount() - manualLottoCount.getLottoCount());

        Lottos manualLottos = LottoManager.generateManualLottos(InputView.inputAsManualLotto(manualLottoCount));
        Lottos autoLottos = LottoManager.generateAutoLottos(autoLottoCount);

        showPurchasedLottos(manualLottoCount, manualLottos, autoLottoCount, autoLottos);
        WinningNumber winningNumber = setWinningNumber();

        Lottos allLottos = LottoManager.concatLottos(autoLottos, manualLottos);
        showFinalResult(purchaseAmount, winningNumber, allLottos);
    }

    private static void showPurchasedLottos(LottoCount manualLottoCount, Lottos manualLottos, LottoCount autoLottoCount, Lottos autoLottos) {
        OutputView.showPurchasedLottoCount(manualLottoCount, autoLottoCount);
        OutputView.showAllLottos(manualLottos, autoLottos);
    }

    private static WinningNumber setWinningNumber() {
        Lotto winningLotto = new Lotto(NumberParser.parseIntoLottoNumbers(InputView.inputAsWinningLotto()));
        LottoNumber bonusNumber = new LottoNumber(NumberParser.parseIntoOneNumber(InputView.inputAsBonusNumber()));
        return new WinningNumber(winningLotto, bonusNumber);
    }

    private static void showFinalResult(PurchaseAmount purchaseAmount, WinningNumber winningNumber, Lottos lottos) {
        Ranks ranks = new Ranks(winningNumber, lottos);
        OutputView.showStatistics(ranks);
        OutputView.showEarningRate(ranks, purchaseAmount);
    }
}
