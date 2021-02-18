package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {
        Money amount = InputView.inputPurchaseMoney();

        int lottoCount = Lotto.ableToBuyWith(amount);
        OutputView.printNumberOfPurchaseLotto(lottoCount);

        Lottos purchased = LottoFactory.generates(new DefaultShuffleStrategy(), lottoCount);
        OutputView.printAllLottos(purchased);

        WinningLotto winningLotto = InputView.inputWinningLotto();
        LottoResults results = purchased.getLottoResults(winningLotto);
        OutputView.printResults(results);

        Money revenue = results.getTotalWinningMoney();
        double earningRate = revenue.calculateEarningRate(amount);
        OutputView.printEarningRate(earningRate);
    }
}
