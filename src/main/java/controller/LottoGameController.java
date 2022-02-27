package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGameController {

    public void run() {
        final int inputMoney = InputView.inputPrice();
        final Lottos purchasedLotto = Store.purchaseLottos(inputMoney);
        OutputView.printPurchasedLotto(purchasedLotto.getLottos());

        final WinningNumbers winningNumber = initWinningNumber();
        final PrizeResult prizeResult = purchasedLotto.prizeResult(inputMoney, winningNumber);

        OutputView.printFinalStatistic(prizeResult);
        OutputView.printEarningRate(prizeResult.getEarningRate());
    }

    private WinningNumbers initWinningNumber() {
        try {
            final WinningNumbers winningNumber = new WinningNumbers(InputView.inputWinningLottoNumbers());
            initBonusNumber(winningNumber);
            return winningNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initWinningNumber();
        }
    }

    private void initBonusNumber(WinningNumbers winningNumber) {
        try {
            winningNumber.addBonusNumber(InputView.inputBonus());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            initBonusNumber(winningNumber);
        }
    }

}
