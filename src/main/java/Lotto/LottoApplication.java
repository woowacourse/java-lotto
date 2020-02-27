package Lotto;

import Lotto.domain.*;
import Lotto.utils.NumberParser;
import Lotto.views.InputView;
import Lotto.views.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(InputView.inputAsPurchaseAmount());
        int purchasedLottoCount = purchaseAmount.calculateLottoAmount();
        OutputView.showPurchasedLottoCount(purchasedLottoCount);

        Lottos purchasedLottos = LottoFactory.generateAutoLottos(purchasedLottoCount);
        OutputView.showPurchasedAutoLottos(purchasedLottos);

        Lotto winningLotto = new Lotto(NumberParser.parseIntoLottoNumbers(InputView.inputAsWinningLotto()));
        LottoNumber bonusNumber = new LottoNumber(NumberParser.parseIntoOneNumber(InputView.inputAsBonusNumber()));
        WinningNumber winningNumber = new WinningNumber(winningLotto, bonusNumber);

        Ranks ranks = purchasedLottos.calculateMultipleRanks(winningNumber);
        OutputView.showStatistics(ranks);
        OutputView.showEarningRate(ranks, purchaseAmount);
    }
}
