package controller;

import domain.LottoMachine;
import domain.LottoNumbers;
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
        List<LottoTicket> lottoTickets = getLottoTickets();

        LottoNumbers winningLottoNumbers = InputView.inputWinningLottoNumbers();
        int bonusNumber = InputView.inputBonusNumber(winningLottoNumbers);

        calculateAndPrintStatistics(lottoTickets, winningLottoNumbers, bonusNumber);
    }

    private List<LottoTicket> getLottoTickets() {
        int purchaseAmount = InputView.inputPurchaseAmount();
        LottoMachine lottoMachine = new LottoMachine();
        List<LottoTicket> lottoTickets =
                lottoMachine.generateLottoTickets(purchaseAmount, new RandomIntegerGenerator());
        OutputView.printLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private void calculateAndPrintStatistics(List<LottoTicket> lottoTickets, LottoNumbers winningLottoNumbers,
                                             int bonusNumber) {
        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets,
                winningLottoNumbers,
                bonusNumber);
        OutputView.printWinningStatistics(winningStatistics);
        Profit profit = statisticsService.calculateProfit(winningStatistics);
        OutputView.printProfit(profit);
    }
}
