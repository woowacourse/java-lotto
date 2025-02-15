package controller;

import domain.DrawResult;
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
        List<LottoTicket> lottoTickets = purchaseLottoTickets();

        DrawResult drawResult = inputDrawResult();

        calculateAndPrintStatistics(lottoTickets, drawResult);
    }
    
    private List<LottoTicket> purchaseLottoTickets() {
        int purchaseAmount = InputView.inputPurchaseAmount();
        LottoMachine lottoMachine = new LottoMachine();
        List<LottoTicket> lottoTickets =
                lottoMachine.generateLottoTickets(purchaseAmount, new RandomIntegerGenerator());
        OutputView.printLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private DrawResult inputDrawResult() {
        LottoNumbers winningLottoNumbers = InputView.inputWinningLottoNumbers();
        int bonusNumber = InputView.inputBonusNumber(winningLottoNumbers);
        return new DrawResult(winningLottoNumbers, bonusNumber);
    }

    private void calculateAndPrintStatistics(List<LottoTicket> lottoTickets, DrawResult drawResult) {
        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets, drawResult);
        OutputView.printWinningStatistics(winningStatistics);
        Profit profit = statisticsService.calculateProfit(winningStatistics);
        OutputView.printProfit(profit);
    }
}
