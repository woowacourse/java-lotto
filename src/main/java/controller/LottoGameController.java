package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGameController {

    public void run() {
        Lottos purchasedLotto = purchaseLotto();

        final WinningNumbers winningNumber = InputView.commonInputProcess(
                () -> new WinningNumbers(InputView.inputWinningLottoNumbers(), InputView.inputBonus()));
        final PrizeResult prizeResult = purchasedLotto.prizeResult(winningNumber);

        OutputView.printFinalStatistic(prizeResult);
        OutputView.printEarningRate(prizeResult.earningRate(purchasedLotto.amountOfLottos()));
    }

    private Lottos purchaseLotto() {
        Store store = new Store();
        final Money money = InputView.commonInputProcess(() -> new Money(InputView.inputPrice()));
        final int numOfManualLotto = InputView.commonInputProcess(
                () -> store.checkAvailableBuy(money, InputView.inputNumOfManualLotto()));
        final Lottos purchasedLotto = InputView.commonInputProcess(
                () -> store.purchaseManualLottos(money, numOfManualLotto, InputView.inputManualLottoNumbers(numOfManualLotto)));
        store.purchaseAutomaticLottos(purchasedLotto, money);
        OutputView.printPurchasedLotto(purchasedLotto.getLottos(), numOfManualLotto);

        return purchasedLotto;
    }

}
