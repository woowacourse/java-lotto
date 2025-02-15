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

        LottoTicket winningLottoTicket = new LottoTicket(InputView.inputWinningLottoTicket());
        int bonusNumber = InputView.inputBonusNumber(winningLottoTicket.getLottoNumbers());

        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets,
                winningLottoTicket,
                bonusNumber);
        OutputView.printWinningStatistics(winningStatistics);
        Profit profit = statisticsService.calculateProfit(winningStatistics);
        OutputView.printProfit(profit);
    }
}
