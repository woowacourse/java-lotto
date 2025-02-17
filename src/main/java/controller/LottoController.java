package controller;

import creator.LottoCreator;
import domain.Lotto;
import domain.Profit;
import domain.Rank;
import domain.Ticket;
import domain.WinningNumber;
import java.util.List;
import java.util.Map;
import domain.LottoManager;
import domain.ResultCalculator;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        int purchaseAmount = inputView.purchaseAmountInput();
        Ticket ticket = purchaseTicketProcess(purchaseAmount);
        List<Lotto> lottos = purchaseLottoProcess(ticket);
        WinningNumber winningNumber = compareWinningNumberProcess();
        Map<Rank, Integer> calculateResult = calculateRankProcess(winningNumber, lottos);
        calculateProfitProcess(calculateResult, purchaseAmount);
    }

    private Ticket purchaseTicketProcess(int purchaseAmount) {
        Ticket ticket = LottoCreator.createTicket(purchaseAmount);
        outputView.printPurchaseResult(ticket);
        return ticket;
    }

    private void calculateProfitProcess(Map<Rank, Integer> calculateResult, int purchaseAmount) {
        Profit profit = LottoCreator.createProfit(calculateResult, purchaseAmount);
        outputView.printProfit(profit.getResult());
    }

    private Map<Rank, Integer> calculateRankProcess(
        WinningNumber winningNumber, List<Lotto> lottos) {
        ResultCalculator resultCalculator = ResultCalculator.create();
        resultCalculator.calculate(winningNumber, lottos);
        Map<Rank, Integer> calculateResult = resultCalculator.getCalculateResult();
        outputView.printWinningStatistic(calculateResult);
        return calculateResult;
    }

    private WinningNumber compareWinningNumberProcess() {
        String winningNumbers = inputView.winningNumbersInput();
        Lotto lotto = LottoCreator.createLotto(winningNumbers);
        int bonusNumber = inputView.bonusNumberInput();
        return LottoCreator.createWinningNumber(lotto, bonusNumber);
    }

    private List<Lotto> purchaseLottoProcess(Ticket ticket) {
        LottoManager lottoManager = LottoManager.create();
        List<Lotto> lottos = lottoManager.purchaseLottoByTicketQuantity(ticket);
        outputView.printLottos(lottos);
        return lottos;
    }
}
