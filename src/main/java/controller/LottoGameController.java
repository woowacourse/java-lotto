package controller;

import domain.Lotto;
import domain.PurchasedLotto;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGameController {

    private PurchasedLotto purchasedLotto;
    private WinningLotto winningLotto;

    public void run() {
        purchasedLotto = new PurchasedLotto(InputView.inputPrice());
        OutputView.printPurchasedLotto(purchasedLotto);
        Lotto lotto  = new Lotto(InputView.inputWinningLottoNumbers());
        int bonus = InputView.inputBonus();
        winningLotto = new WinningLotto(lotto, bonus);
        purchasedLotto.calculatePrizeResult(winningLotto);
        OutputView.printFinalStatistic(purchasedLotto.getPrizeResult());
        OutputView.printEarningRate(purchasedLotto.calculateEarningRate());
    }

}
