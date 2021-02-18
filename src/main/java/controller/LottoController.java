package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {
        Money amount = InputView.inputPurchaseMoney();

        int lottoCount = Lotto.getNumberOfAvailablePurchases(amount);
        OutputView.printNumberOfPurchasedLotto(lottoCount);

        Lottos lottos = LottoFactory.generates(new DefaultShuffleStrategy(), lottoCount);
        OutputView.printAllLottoList(lottos);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        LottoResults results = lottos.getLottoResults(winningLotto);
        OutputView.printResults(results);

        Money revenue = results.getTotalWinningMoney();
        double earningRate = Money.calculateEarningRate(revenue, amount);
        OutputView.printEarningRate(earningRate);
    }
}
