package controller;

import domain.DrawResult;
import domain.LottoTickets;
import domain.Payment;
import domain.RandomIntegerGenerator;
import domain.WinningResult;
import domain.WinningStatistics;
import service.IssuingService;
import service.StatisticsService;
import view.InputView;
import view.OutputView;

public class MainController {
    private final IssuingService issuingService;
    private final StatisticsService statisticsService;

    public MainController(IssuingService issuingService, StatisticsService statisticsService) {
        this.issuingService = issuingService;
        this.statisticsService = statisticsService;
    }

    public void run() {
        LottoTickets lottoTickets = purchaseLottoTickets();
        OutputView.printLottoTickets(lottoTickets);

        DrawResult drawResult = InputView.inputDrawResult();

        WinningResult winningResult = calculateWinningResult(lottoTickets,
                drawResult);
        OutputView.printWinningResult(winningResult);
    }

    private LottoTickets purchaseLottoTickets() {
        Payment payment = InputView.inputPayment();
        return issuingService.issueLottoTickets(payment, new RandomIntegerGenerator());
    }

    private WinningResult calculateWinningResult(LottoTickets lottoTickets,
                                                 DrawResult drawResult) {
        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets, drawResult);
        double profit = statisticsService.calculateProfit(winningStatistics);
        return new WinningResult(winningStatistics, profit);
    }
}
