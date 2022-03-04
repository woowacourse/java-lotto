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
        OutputView.printEarningRate(prizeResult.earningRate(purchasedLotto.totalPurchasePrice()));
    }

    private Lottos purchaseLotto() {
        LottoMachine lottoMachine = new LottoMachine();
        final Money money = InputView.commonInputProcess(() -> new Money(InputView.inputMoney()));
        final int numOfManualLotto = InputView.commonInputProcess(
                () -> lottoMachine.checkAvailableBuy(money, InputView.inputNumOfManualLotto()));
        final Lottos purchasedLotto = InputView.commonInputProcess(
                () -> lottoMachine.purchaseManualLottos(money, numOfManualLotto, InputView.inputManualLottoNumbers(numOfManualLotto)));
        lottoMachine.purchaseAutomaticLottos(purchasedLotto, money);
        OutputView.printPurchasedLotto(purchasedLotto.getLottos(), numOfManualLotto);

        return purchasedLotto;
    }

}
