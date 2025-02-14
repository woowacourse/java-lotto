package controller;

import domain.Lotto;
import domain.LottoStats;
import domain.WinningLotto;
import service.LottoService;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private static final LottoService lottoService = new LottoService();

    public static void run() {
        try{
            int purchaseAmount = InputView.inputPurchaseAmount();
            List<Lotto> lottos = lottoService.buyLottos(purchaseAmount);
            OutputView.printLottos(lottos);

            List<Integer> winningNumbers = InputView.inputWinningNumbers();
            WinningLotto winningLotto = new WinningLotto(winningNumbers,InputView.inputBonusBall(winningNumbers));

            LottoStats lottoStats = new LottoStats();
            lottoService.calcResult(lottoStats,lottos,winningLotto);

            OutputView.printLottoStats(lottoStats);
            OutputView.printEarningRate(lottoStats, purchaseAmount);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
