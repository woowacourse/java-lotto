package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {
        Money purchaseMoney = InputView.inputPurchaseMoney();
        CountOfPurchasingLotto numberOfTotalLotto = Lotto.calculateLottoNumber(purchaseMoney);
        CountOfPurchasingLotto numberOfPassiveLottoNumber = InputView.inputPurchasingPassiveLottoNumber();

        OutputView.printNumberOfPurchaseLotto(numberOfTotalLotto);

        Lottos lottos = LottoFactory.generates(new DefaultShuffleStrategy(), numberOfTotalLotto);
        OutputView.printAllLottos(lottos);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        LottoResults results = lottos.getLottoResults(winningLotto);
        OutputView.printResults(results);

        Money earningMoney = results.getTotalWinningMoney();
        OutputView.printEarningRate(earningMoney.calculateEarningRate(purchaseMoney));
    }
}
