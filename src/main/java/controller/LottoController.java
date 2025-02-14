package controller;

import domain.Lotto;
import domain.Rank;
import domain.Ticket;
import domain.WinningNumber;
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

    public void start() {
        int purchaseAmount = inputView.purchaseAmountInput();
        Ticket ticket = ticketProcess(purchaseAmount);
        List<Lotto> lottos = lottoProcess(ticket);
        WinningNumber winningNumber = winningNumberProcess();
        Map<Rank, Integer> rankResult = calculateRankProcess(winningNumber, lottos);
        profitProcess(rankResult, purchaseAmount);
    }

    private Ticket ticketProcess(int purchaseAmount) {
        Ticket ticket = lottoService.makeTicket(purchaseAmount);
        outputView.printPurchaseResult(ticket);
        return ticket;
    }

    private void profitProcess(Map<Rank, Integer> rankResult, int purchaseAmount) {
        double calculateRate = lottoService.calculateProfit(rankResult, purchaseAmount);
        outputView.printProfit(calculateRate);
    }

    private Map<Rank, Integer> calculateRankProcess(WinningNumber winningNumber, List<Lotto> lottos) {
        lottoService.calculateRank(winningNumber, lottos);
        Map<Rank, Integer> rankResult = lottoService.getRankResult();
        outputView.printWinningStatistic(rankResult);
        return rankResult;
    }

    private WinningNumber winningNumberProcess() {
        String winningNumbers = inputView.winningNumbersInput();
        Lotto lotto = lottoService.makeLotto(winningNumbers);
        int bonusNumber = inputView.bonusNumberInput();
        return lottoService.makeWinningNumber(lotto, bonusNumber);
    }

    private List<Lotto> lottoProcess(Ticket ticket) {
        lottoService.saveLotto(ticket);
        List<Lotto> lottos = lottoService.getLottos();
        outputView.printLottos(lottos);
        return lottos;
    }
}
