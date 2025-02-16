package controller;

import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoResultCalculator;
import domain.Rank;
import domain.TicketMachine;
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
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator();

        int purchaseAmount = inputView.purchaseAmountInput();
        TicketMachine ticketMachine = generateTicket(purchaseAmount);
        List<Lotto> lottoBundle = generateLottoBundle(lottoGenerator, ticketMachine);
        WinningInfo winningInfo = generateWinningInfo(lottoGenerator);
        Map<Rank, Integer> rankResult = calculateMatchingRank(lottoResultCalculator, winningInfo, lottoBundle);
        calculateProfit(lottoResultCalculator, rankResult, purchaseAmount);
    }

    private TicketMachine generateTicket(int purchaseAmount) {
        TicketMachine ticketMachine = TicketMachine.create(purchaseAmount);
        outputView.printPurchaseResult(ticketMachine);
        return ticketMachine;
    }

    private List<Lotto> generateLottoBundle(LottoGenerator lottoGenerator, TicketMachine ticketMachine) {
        List<Lotto> lottoBundle = lottoGenerator.createLottoBundleForTicket(ticketMachine);
        outputView.printLottos(lottoBundle);
        return lottoBundle;
    }

    private WinningInfo generateWinningInfo(LottoGenerator lottoGenerator) {
        String winningNumbers = inputView.winningNumbersInput();
        Lotto lotto = lottoGenerator.createWinningLotto(winningNumbers);
        int bonusNumber = inputView.bonusNumberInput();
        return lottoGenerator.createWinningInfo(lotto, bonusNumber);
    }

    private Map<Rank, Integer> calculateMatchingRank(LottoResultCalculator lottoResultCalculator,
                                                     WinningInfo winningInfo, List<Lotto> lottoBundle) {
        Map<Rank, Integer> rankResult = lottoResultCalculator.calculateMatchingRank(winningInfo, lottoBundle);
        outputView.printWinningStatistic(rankResult);
        return rankResult;
    }

    private void calculateProfit(LottoResultCalculator lottoResultCalculator, Map<Rank, Integer> rankResult,
                                 int purchaseAmount) {
        double calculateRate = lottoResultCalculator.calculateProfit(rankResult, purchaseAmount);
        outputView.printProfit(calculateRate);
    }
}
