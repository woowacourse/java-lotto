package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {
        Money purchaseMoney = InputView.inputPurchaseMoney();

        int numberOfLotto = Lotto.calculateLottoNumber(purchaseMoney);
        OutputView.printNumberOfPurchaseLotto(numberOfLotto);

        Lottos boughtLottos = LottoFactory.generates(new DefaultShuffleStrategy(), numberOfLotto);
        OutputView.printAllLottos(boughtLottos);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        LottoResults results = boughtLottos.getLottoResults(winningLotto);

        OutputView.printResults(results);

        Money earningMoney = results.getTotalWinningMoney();
        double earningRate = earningMoney.calculateEarningRate(purchaseMoney);
        OutputView.printEarningRate(earningRate);
    }
}
