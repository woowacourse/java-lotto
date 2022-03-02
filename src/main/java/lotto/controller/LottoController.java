package lotto.controller;

import java.util.List;
import lotto.domain.LottoTicketFactory;
import lotto.domain.vo.PurchaseAmount;
import lotto.domain.WinningStats;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.vo.ManualTicketSize;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView = InputView.INSTANCE;
    private final OutputView outputView = OutputView.INSTANCE;
    private final LottoTicketFactory lottoTicketFactory = LottoTicketFactory.INSTANCE;

    private final ThreadLocal<PurchaseAmount> purchaseAmountRepository = new ThreadLocal<>();
    private final ThreadLocal<List<LottoTicket>> lottoTicketsRepository = new ThreadLocal<>();
    private final ThreadLocal<WinningStats> winningStatsRepository = new ThreadLocal<>();
    private final ThreadLocal<List<String>> manualTicketNumbersRepository = new ThreadLocal<>();

    public void buyTicket() {
        PurchaseAmount purchaseAmount = inputView.commonInputProcess(() -> new PurchaseAmount(inputView.inputMoney()));
        purchaseAmountRepository.set(purchaseAmount);
        ManualTicketSize manualTicketSize = inputView.commonInputProcess(() ->
                new ManualTicketSize(inputView.inputLottoTicketSizeManually(), purchaseAmount)
        );
        List<String> manualTicketNumbers = inputView.commonInputProcess(
                () -> inputView.inputTicketNumbersManually(manualTicketSize));
        manualTicketNumbersRepository.set(manualTicketNumbers);
    }

    public void showLottoTickets() {
        PurchaseAmount purchaseAmount = purchaseAmountRepository.get();
        List<String> manualTicketNumbers = manualTicketNumbersRepository.get();
        List<LottoTicket> lottoTickets = lottoTicketFactory.createTickets(purchaseAmount, manualTicketNumbers);
        lottoTicketsRepository.set(lottoTickets);
        outputView.printPurchasedTickets(lottoTickets, manualTicketNumbers.size());
    }

    public void showWinningStats() {
        PurchaseAmount purchaseAmount = purchaseAmountRepository.get();
        List<LottoTicket> lottoTickets = lottoTicketsRepository.get();
        LottoTicket lottoTicket = inputView.commonInputProcess(() -> new LottoTicket(inputView.inputWinningNumbers()));
        LottoNumber bonusBall = inputView.commonInputProcess(() -> new LottoNumber(inputView.inputBonusBall()));
        WinningNumbers winningNumbers = inputView.commonInputProcess(() -> new WinningNumbers(lottoTicket, bonusBall));
        WinningStats winningStats = new WinningStats(lottoTickets, winningNumbers);
        winningStatsRepository.set(winningStats);

        outputView.printWinningStats(winningStats, purchaseAmount);
    }

    public void clearRepository() {
        purchaseAmountRepository.remove();
        lottoTicketsRepository.remove();
        winningStatsRepository.remove();
        inputView.closeResource();
    }
}
