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
        Lottos manualLottos = LottoFactory.generateManualLottos(InputView.inputAsManualLotto(manualLottoCount));

        LottoCount autoLottoCount = new LottoCount(totalLottoCount.getLottoCount() - manualLottoCount.getLottoCount());
        Lottos autoLottos = LottoFactory.generateAutoLottos(autoLottoCount);

        showPurchasedLottos(manualLottoCount, manualLottos, autoLottoCount, autoLottos);

        Lottos allLottos = LottoConcat.concatLottos(autoLottos, manualLottos);

        Lotto winningLotto = new Lotto(NumberParser.parseIntoLottoNumbers(InputView.inputAsWinningLotto()));
        LottoNumber bonusNumber = new LottoNumber(NumberParser.parseIntoOneNumber(InputView.inputAsBonusNumber()));
        WinningNumber winningNumber = new WinningNumber(winningLotto, bonusNumber);

        Ranks ranksOfAllLottos = new Ranks(winningNumber, allLottos);
        showFinalResult(purchaseAmount, ranksOfAllLottos);
    }

    private static void showFinalResult(PurchaseAmount purchaseAmount, Ranks ranksOfAllLottos) {
        OutputView.showStatistics(ranksOfAllLottos);
        OutputView.showEarningRate(ranksOfAllLottos, purchaseAmount);
    }

    private static void showPurchasedLottos(LottoCount manualLottoCount, Lottos manualLottos, LottoCount autoLottoCount, Lottos autoLottos) {
        OutputView.showPurchasedLottoCount(manualLottoCount, autoLottoCount);
        OutputView.showAllLottos(manualLottos, autoLottos);
    }
}
