package Lotto;

import Lotto.domain.*;
import Lotto.utils.NumberParser;
import Lotto.views.InputView;
import Lotto.views.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(InputView.inputAsPurchaseAmount());
        LottoAmount totalLottoAmount = new LottoAmount(purchaseAmount.calculateLottoAmount());

        LottoAmount manualLottoAmount = new LottoAmount(InputView.inputAsManualLottoAmount());
        Lottos manualLottos = LottoFactory.generateManualLottos(InputView.inputAsManualLotto(manualLottoAmount));

        LottoAmount autoLottoAmount = new LottoAmount(totalLottoAmount.getLottoAmount() - manualLottoAmount.getLottoAmount());
        Lottos autoLottos = LottoFactory.generateAutoLottos(autoLottoAmount);

        OutputView.showPurchasedLottoCount(manualLottoAmount, autoLottoAmount);
        OutputView.showAllLottos(manualLottos ,autoLottos);

        Lottos allLottos = LottoFactory.concatLottos(autoLottos, manualLottos);

        Lotto winningLotto = new Lotto(NumberParser.parseIntoLottoNumbers(InputView.inputAsWinningLotto()));
        LottoNumber bonusNumber = new LottoNumber(NumberParser.parseIntoOneNumber(InputView.inputAsBonusNumber()));
        WinningNumber winningNumber = new WinningNumber(winningLotto, bonusNumber);

        Ranks ranksOfAllLottos = allLottos.calculateMultipleRanks(winningNumber);
        OutputView.showStatistics(ranksOfAllLottos);
        OutputView.showEarningRate(ranksOfAllLottos, purchaseAmount);
    }
}
