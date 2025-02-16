package controller;

import domain.DrawResult;
import domain.LottoMachine;
import domain.LottoTickets;
import domain.Payment;
import domain.StatisticsService;
import domain.WinningResult;
import domain.WinningStatistics;
import view.InputView;
import view.OutputView;

public class MainController {
    private final StatisticsService statisticsService;
    private final LottoMachine lottoMachine;

    public MainController(StatisticsService statisticsService, LottoMachine lottoMachine) {
        this.statisticsService = statisticsService;
        this.lottoMachine = lottoMachine;
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
        return lottoMachine.generateLottoTickets(payment);
    }

    private WinningResult calculateWinningResult(LottoTickets lottoTickets,
                                                 DrawResult drawResult) {
        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets, drawResult);
        double profit = statisticsService.calculateProfit(winningStatistics);
        return new WinningResult(winningStatistics, profit);
    }
}
