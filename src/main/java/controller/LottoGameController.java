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
        initWinningNumber();
        purchasedLotto.calculatePrizeResult(winningLotto);
        OutputView.printFinalStatistic(purchasedLotto.getPrizeResult());
        OutputView.printEarningRate(purchasedLotto.calculateEarningRate());
    }

    private void initWinningNumber() {
        winningLotto = new WinningLotto(InputView.inputWinningLottoNumbers());
        initBonusNumber();
    }

    private void initBonusNumber() {
        try {
            winningLotto.addBonusNumber(InputView.inputBonus());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            initBonusNumber();
        }
    }

}
