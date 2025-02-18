package controller;

import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoPurchaseManager;
import domain.LottoResultCalculator;
import domain.Rank;
import domain.WinningInfo;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            int purchaseAmount = inputView.purchaseAmountInput();
            List<Lotto> lottoBundle = purchaseLottoBundle(purchaseAmount);
            WinningInfo winningInfo = generateWinningInfo();
            Map<Rank, Integer> rankResult = calculateMatchingRank(winningInfo, lottoBundle);
            calculateProfit(rankResult, purchaseAmount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Lotto> purchaseLottoBundle(int purchaseAmount) {
        int lottoQuantity = LottoPurchaseManager.purchaseLottoByAmount(purchaseAmount);
        outputView.printPurchaseResult(lottoQuantity);
        List<Lotto> lottoBundle = LottoGenerator.createLottoBundleForQuantity(lottoQuantity);
        outputView.printLottos(lottoBundle);
        return lottoBundle;
    }

    private WinningInfo generateWinningInfo() {
        String winningNumbers = inputView.winningNumbersInput();
        Lotto lotto = LottoGenerator.createWinningLotto(winningNumbers);
        int bonusNumber = inputView.bonusNumberInput();
        return LottoGenerator.createWinningInfo(lotto, bonusNumber);
    }

    private Map<Rank, Integer> calculateMatchingRank(WinningInfo winningInfo, List<Lotto> lottoBundle) {
        Map<Rank, Integer> rankResult = LottoResultCalculator.calculateMatchingRank(winningInfo, lottoBundle);
        outputView.printWinningStatistic(rankResult);
        return rankResult;
    }

    private void calculateProfit(Map<Rank, Integer> rankResult,
                                 int purchaseAmount) {
        double calculateRate = LottoResultCalculator.calculateProfit(rankResult, purchaseAmount);
        outputView.printProfit(calculateRate);
    }
}
