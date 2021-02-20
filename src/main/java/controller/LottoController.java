package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {
        Money purchaseMoney = InputView.inputPurchaseMoney();
        int numberOfLotto = Lotto.calculateLottoNumber(purchaseMoney);
        OutputView.printNumberOfPurchaseLotto(numberOfLotto);

        Lottos lottos = LottoFactory.generates(new DefaultShuffleStrategy(), numberOfLotto);
        OutputView.printAllLottos(lottos);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        LottoResults results = lottos.getLottoResults(winningLotto);
        OutputView.printResults(results);

        Money earningMoney = results.getTotalWinningMoney();
        OutputView.printEarningRate(earningMoney.calculateEarningRate(purchaseMoney));
    }
}
