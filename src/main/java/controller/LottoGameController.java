package controller;

import domain.PurchasedLotto;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoGameController {

    private PurchasedLotto purchasedLotto;
    private WinningLotto winningLotto;

    public void run() {
        purchasedLotto = new PurchasedLotto(InputView.inputPrice());
        OutputView.printPurchasedLotto(purchasedLotto);
        winningLotto = new WinningLotto(InputView.inputWinningLottoNumbers(), InputView.inputBonus());
        purchasedLotto.calculatePrizeResult(winningLotto);
        OutputView.printFinalStatistic(purchasedLotto.getPrizeResult());
        OutputView.printEarningRate(purchasedLotto.calculateEarningRate());
    }

}
