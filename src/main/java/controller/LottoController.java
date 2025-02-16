package controller;

import domain.Lotto;
import domain.Rank;
import domain.Ticket;
import domain.WinningInfo;
import java.util.List;
import java.util.Map;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        int purchaseAmount = inputView.purchaseAmountInput();
        Ticket ticket = generateTicket(purchaseAmount);
        List<Lotto> lottoBundle = generateLottoBundle(ticket);
        WinningInfo winningInfo = generateWinningInfo();
        Map<Rank, Integer> rankResult = calculateMatchingRank(winningInfo, lottoBundle);
        calculateProfit(rankResult, purchaseAmount);
    }

    private Ticket generateTicket(int purchaseAmount) {
        Ticket ticket = lottoService.createTicket(purchaseAmount);
        outputView.printPurchaseResult(ticket);
        return ticket;
    }

    private List<Lotto> generateLottoBundle(Ticket ticket) {
        lottoService.createLottoBundleForTicket(ticket);
        List<Lotto> lottoBundle = lottoService.getLottoBundle();
        outputView.printLottos(lottoBundle);
        return lottoBundle;
    }

    private WinningInfo generateWinningInfo() {
        String winningNumbers = inputView.winningNumbersInput();
        Lotto lotto = lottoService.createLotto(winningNumbers);
        int bonusNumber = inputView.bonusNumberInput();
        return lottoService.createWinningNumber(lotto, bonusNumber);
    }

    private Map<Rank, Integer> calculateMatchingRank(WinningInfo winningInfo, List<Lotto> lottoBundle) {
        Map<Rank, Integer> rankResult = lottoService.calculateMatchingRank(winningInfo, lottoBundle);
        outputView.printWinningStatistic(rankResult);
        return rankResult;
    }

    private void calculateProfit(Map<Rank, Integer> rankResult, int purchaseAmount) {
        double calculateRate = lottoService.calculateProfit(rankResult, purchaseAmount);
        outputView.printProfit(calculateRate);
    }
}
