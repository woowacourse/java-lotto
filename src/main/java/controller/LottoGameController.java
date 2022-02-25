package controller;

import domain.PrizeResult;
import domain.PurchasedLotto;
import domain.WinningNumber;
import view.InputView;
import view.OutputView;

public class LottoGameController {

    public void run() {
        PurchasedLotto purchasedLotto = new PurchasedLotto(InputView.inputPrice());
        OutputView.printPurchasedLotto(purchasedLotto);

        PrizeResult prizeResult = purchasedLotto.prizeResult(initWinningNumber());
        OutputView.printFinalStatistic(prizeResult);
        OutputView.printEarningRate(prizeResult.getEarningRate());
    }

    private WinningNumber initWinningNumber() {
        try {
            WinningNumber winningNumber = new WinningNumber(InputView.inputWinningLottoNumbers());
            initBonusNumber(winningNumber);
            return winningNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initWinningNumber();
        }
    }

    private void initBonusNumber(WinningNumber winningNumber) {
        try {
            winningNumber.addBonusNumber(InputView.inputBonus());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            initBonusNumber(winningNumber);
        }
    }

}
