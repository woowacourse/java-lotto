package controller;

import domain.Lotto;
import domain.Rank;
import domain.Ticket;
import domain.WinningInfo;
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
        WinningInfo winningInfo = winningInfoProcess();
        Map<Rank, Integer> rankResult = calculateRankProcess(winningInfo, lottos);
        profitProcess(rankResult, purchaseAmount);
    }

    private Ticket ticketProcess(int purchaseAmount) {
        Ticket ticket = lottoService.createTicket(purchaseAmount);
        outputView.printPurchaseResult(ticket);
        return ticket;
    }

    private void profitProcess(Map<Rank, Integer> rankResult, int purchaseAmount) {
        double calculateRate = lottoService.calculateProfit(rankResult, purchaseAmount);
        outputView.printProfit(calculateRate);
    }

    private Map<Rank, Integer> calculateRankProcess(WinningInfo winningInfo, List<Lotto> lottos) {
        Map<Rank, Integer> rankResult = lottoService.calculateRank(winningInfo, lottos);
        outputView.printWinningStatistic(rankResult);
        return rankResult;
    }

    private WinningInfo winningInfoProcess() {
        String winningNumbers = inputView.winningNumbersInput();
        Lotto lotto = lottoService.createLotto(winningNumbers);
        int bonusNumber = inputView.bonusNumberInput();
        return lottoService.createWinningNumber(lotto, bonusNumber);
    }

    private List<Lotto> lottoProcess(Ticket ticket) {
        lottoService.createLottos(ticket);
        List<Lotto> lottos = lottoService.getLottos();
        outputView.printLottos(lottos);
        return lottos;
    }
}
