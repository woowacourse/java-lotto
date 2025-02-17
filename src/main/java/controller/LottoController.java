package controller;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoStats;
import domain.Rank;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    public static void run() {
        int purchaseAmount = InputView.inputPurchaseAmount();
        List<Lotto> lottos = LottoFactory.makeLotto(purchaseAmount);
        OutputView.printLottos(lottos);
        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        int bonusBall = InputView.inputBonusBall(winningNumbers);
        LottoStats lottoStats = Rank.makeLottoResult(lottos,winningNumbers,bonusBall);
        OutputView.printLottoStats(lottoStats);
        OutputView.printEarningRate(lottoStats, purchaseAmount);
    }
}
