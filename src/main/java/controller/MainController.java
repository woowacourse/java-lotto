package controller;

import domain.LottoMachine;
import domain.LottoTicket;
import domain.Profit;
import domain.RandomIntegerGenerator;
import domain.StatisticsService;
import domain.WinningStatistics;
import java.util.List;
import view.InputView;
import view.OutputView;

public class MainController {
    private final StatisticsService statisticsService;

    public MainController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public void run() {
        int purchaseAmount = InputView.inputPurchaseAmount();
        LottoMachine lottoMachine = new LottoMachine();
        List<LottoTicket> lottoTickets =
                lottoMachine.generateLottoTickets(purchaseAmount, new RandomIntegerGenerator());
        OutputView.printLottoTickets(lottoTickets);

        List<Integer> winningNumbers = InputView.inputWinningLottoTicket();
        int bonusNumber = InputView.inputBonusNumber(winningNumbers);

        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets, winningNumbers,
                bonusNumber);
        OutputView.printWinningStatistics(winningStatistics);
        Profit profit = statisticsService.calculateProfit(winningStatistics, purchaseAmount);
        OutputView.printProfit(profit);
    }
}
