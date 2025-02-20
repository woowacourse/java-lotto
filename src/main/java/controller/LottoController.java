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
        int purchaseAmount = getPurchaseAmount();
        List<Lotto> lottos = LottoFactory.makeLotto(purchaseAmount);
        OutputView.printLottos(lottos);
        List<Integer> winningNumbers = getWinningNumbers();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, getBonusBall(winningNumbers));
        LottoStats lottoStats = new LottoStats(winningLotto);
        lottoStats.calculateResult(lottos);
        OutputView.printLottoStats(lottoStats);
        OutputView.getEarningRateMessage(lottoStats, purchaseAmount);
    }

    private static int getPurchaseAmount() {
        while (true) {
            try {
                return InputView.inputPurchaseAmount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static List<Integer> getWinningNumbers() {
        while (true) {
            try {
                return InputView.inputWinningNumbers();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getBonusBall(List<Integer> winningNumbers) {
        while (true) {
            try {
                return InputView.inputBonusBall(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
