package controller;

import domain.DrawResult;
import domain.LottoMachine;
import domain.LottoNumbers;
import domain.LottoTickets;
import domain.Profit;
import domain.RandomIntegerGenerator;
import domain.StatisticsService;
import domain.WinningStatistics;
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

        calculateAndPrintStatistics(lottoTickets, drawResult);
    }

    private LottoTickets purchaseLottoTickets() {
        int purchaseAmount = InputView.inputPurchaseAmount();
        LottoMachine lottoMachine = new LottoMachine();
        return lottoMachine.generateLottoTickets(purchaseAmount, new RandomIntegerGenerator());
    }

    private DrawResult inputDrawResult() {
        LottoNumbers winningLottoNumbers = InputView.inputWinningLottoNumbers();
        int bonusNumber = InputView.inputBonusNumber(winningLottoNumbers);
        return new DrawResult(winningLottoNumbers, bonusNumber);
    }

    private void calculateAndPrintStatistics(LottoTickets lottoTickets, DrawResult drawResult) {
        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottoTickets, drawResult);
        Profit profit = statisticsService.calculateProfit(winningStatistics);
        OutputView.printWinningStatistics(winningStatistics);
        OutputView.printProfit(profit);
    }
}
