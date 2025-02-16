package controller;

import domain.DrawResult;
import domain.LottoMachine;
import domain.LottoNumbers;
import domain.LottoTickets;
import domain.Profit;
import domain.RandomIntegerGenerator;
import domain.StatisticsService;
import domain.WinningStatistics;
import domain.WinningStatisticsAndProfit;
import util.RetryHandler;
import view.InputView;
import view.OutputView;

public class MainController {
    private final StatisticsService statisticsService;

    public MainController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public void run() {
        LottoTickets lottoTickets = purchaseLottoTickets();
        OutputView.printLottoTickets(lottoTickets);

        DrawResult drawResult = inputDrawResult();

        WinningStatisticsAndProfit winningStatisticsAndProfit = calculateWinningStatisticsAndProfit(lottoTickets,
                drawResult);
        OutputView.printWinningStatisticsAndProfit(winningStatisticsAndProfit);
    }

    private LottoTickets purchaseLottoTickets() {
        int purchaseAmount = InputView.inputPurchaseAmount();
        LottoMachine lottoMachine = new LottoMachine();
        return lottoMachine.generateLottoTickets(purchaseAmount, new RandomIntegerGenerator());
    }

    private DrawResult inputDrawResult() {
        return (DrawResult) RetryHandler.retryUntilSuccessWithReturn(() -> {
            LottoNumbers winningLottoNumbers = InputView.inputWinningLottoNumbers();
            int bonusNumber = InputView.inputBonusNumber();
            return new DrawResult(winningLottoNumbers, bonusNumber);
        });
    }

    private WinningStatisticsAndProfit calculateWinningStatisticsAndProfit(LottoTickets lottoTickets,
                                                                           DrawResult drawResult) {
        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets, drawResult);
        Profit profit = statisticsService.calculateProfit(winningStatistics);
        return new WinningStatisticsAndProfit(winningStatistics, profit);
    }
}
