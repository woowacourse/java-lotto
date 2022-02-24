package controller;

import domain.PurchasedLotto;
import domain.WinningNumber;
import view.InputView;
import view.OutputView;

public class LottoGameController {

    private PurchasedLotto purchasedLotto;
    private WinningNumber winningNumber;

    public void run() {
        purchasedLotto = new PurchasedLotto(InputView.inputPrice());
        OutputView.printPurchasedLotto(purchasedLotto);
        initWinningNumber();
        purchasedLotto.calculatePrizeResult(winningNumber);
        OutputView.printFinalStatistic(purchasedLotto.getPrizeResult());
        OutputView.printEarningRate(purchasedLotto.calculateEarningRate());
    }

    private void initWinningNumber() {
        winningNumber = new WinningNumber(InputView.inputWinningLottoNumbers());
        initBonusNumber();
    }

    private void initBonusNumber() {
        try {
            winningNumber.addBonusNumber(InputView.inputBonus());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            initBonusNumber();
        }
    }

}
