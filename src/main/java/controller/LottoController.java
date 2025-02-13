package controller;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoStats;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    public static void run() {
        int purchaseAmount = InputView.inputPurchaseAmount();
        List<Lotto> lottos = LottoFactory.makeLotto(purchaseAmount);
        OutputView.printLottos(lottos);
        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        LottoStats lottoStats = new LottoStats(winningNumbers, InputView.inputBonusBall(winningNumbers));
        lottoStats.calculateResult(lottos);
        OutputView.printLottoStats(lottoStats);
        OutputView.printEarningRate(lottoStats, purchaseAmount);
    }
}
