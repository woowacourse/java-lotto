package controller;

import domain.Lottos;
import domain.PrizeResult;
import domain.Store;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

public class LottoGameController {

    public void run() {
        final Lottos purchasedLotto = InputView.commonInputProcess(
                () -> Store.purchaseLottos(InputView.inputPrice()));
        OutputView.printPurchasedLotto(purchasedLotto.getLottos());

        final WinningNumbers winningNumber = InputView.commonInputProcess(
                () -> new WinningNumbers(InputView.inputWinningLottoNumbers(), InputView.inputBonus()));
        final PrizeResult prizeResult = purchasedLotto.prizeResult(winningNumber);

        OutputView.printFinalStatistic(prizeResult);
        OutputView.printEarningRate(prizeResult.earningRate(purchasedLotto.amountOfLottos()));
    }

}
