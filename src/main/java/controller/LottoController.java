package controller;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoStats;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    public static void run() {
        int purchaseAmount = InputView.inputPurchaseAmount();
        List<Lotto> lottos = LottoFactory.makeLotto(purchaseAmount);
        OutputView.printLottos(lottos);
        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        WinningLotto winnigLotto = new WinningLotto(winningNumbers, InputView.inputBonusBall(winningNumbers));
        LottoStats lottoStats = new LottoStats(winnigLotto);
        lottoStats.calculateResult(lottos);
        OutputView.printLottoStats(lottoStats);
        OutputView.printEarningRate(lottoStats, purchaseAmount);
    }
}
