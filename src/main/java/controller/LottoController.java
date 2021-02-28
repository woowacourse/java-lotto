package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {
        Money purchaseMoney = InputView.inputPurchaseMoney();
        int numberOfTotalLotto = Lotto.calculateLottoNumber(purchaseMoney);
        int numberOfPassiveLotto = InputView.inputPurchasingPassiveLottoNumber();
        int numberOfAutoLotto = numberOfTotalLotto - numberOfPassiveLotto;
        Lottos lottos = LottoFactory.generatesPassiveLottos(InputView.inputPurchasingPassiveLottos(numberOfPassiveLotto));
        lottos.addAll(LottoFactory.generateAutoLottos(new DefaultShuffleStrategy(), numberOfAutoLotto));

        OutputView.printNumberOfPurchaseLotto(numberOfPassiveLotto, numberOfAutoLotto);
        OutputView.printAllLottos(lottos);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        LottoResults results = lottos.getLottoResults(winningLotto);
        OutputView.printResults(results);

        Money earningMoney = results.getTotalWinningMoney();
        OutputView.printEarningRate(earningMoney.calculateEarningRate(purchaseMoney));
    }
}
