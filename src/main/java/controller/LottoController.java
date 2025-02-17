package controller;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import creator.LottoCreator;
import domain.Lotto;
import domain.Profit;
import domain.Rank;
import domain.Ticket;
import domain.WinningNumber;
import java.util.List;
import java.util.Map;
import repository.LottoRepository;
import repository.LottoResultRepository;
import utils.RandomNumber;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoRepository lottoRepository;
    private final LottoResultRepository lottoResultRepository;

    public LottoController(InputView inputView, OutputView outputView, LottoRepository lottoRepository,
        LottoResultRepository lottoResultRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoRepository = lottoRepository;
        this.lottoResultRepository = lottoResultRepository;
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
        lottoResultRepository.add(winningNumber, lottos);
        Map<Rank, Integer> calculateResult = lottoResultRepository.getCalculateResult();
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
        for (int i = 0; i < ticket.getQuantity(); i++) {
            List<Integer> numbers = RandomNumber.generateNumbers(WINNING_NUMBERS_REQUIRED);
            Lotto lotto = Lotto.from(numbers);
            lottoRepository.addLotto(lotto);
        }
        List<Lotto> lottos = lottoRepository.getLottos();
        outputView.printLottos(lottos);
        return lottos;
    }
}
