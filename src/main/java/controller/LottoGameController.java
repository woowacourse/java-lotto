package controller;

import domain.PurchasedLotto;
import domain.WinningNumber;
import view.InputView;
import view.OutputView;

public class LottoGameController {

    private PurchasedLotto purchasedLotto;
    private WinningNumber winningNumber;

    public void start() {
        purchasedLotto = new PurchasedLotto(InputView.inputPrice());
        OutputView.printPurchasedLotto(purchasedLotto);
        initWinningNumber();
        purchasedLotto.calculateWinning(winningNumber);
        OutputView.printFinalStatistic(purchasedLotto.getFinalResult());
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
