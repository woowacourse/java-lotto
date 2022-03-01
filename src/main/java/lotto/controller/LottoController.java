package lotto.controller;

import java.util.List;
import lotto.domain.LottoTicketFactory;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningStats;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.view.InputView;
import lotto.view.InputView.IndividualInput;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView = InputView.INSTANCE;
    private final OutputView outputView = OutputView.INSTANCE;

    private final LottoTicketFactory lottoTicketFactory = LottoTicketFactory.INSTANCE;

    private final ThreadLocal<PurchaseAmount> purchaseAmountRepository = new ThreadLocal<>();
    private final ThreadLocal<List<LottoTicket>> lottoTicketsRepository = new ThreadLocal<>();
    private final ThreadLocal<WinningStats> winningStatsRepository = new ThreadLocal<>();

    public void buyTicket() {
        IndividualInput<PurchaseAmount> individualInputs = () -> new PurchaseAmount(inputView.inputMoney());
        PurchaseAmount purchaseAmount = inputView.commonInputProcess(individualInputs);
        purchaseAmountRepository.set(purchaseAmount);
    }

    public void showLottoTickets() {
        PurchaseAmount purchaseAmount = purchaseAmountRepository.get();
        List<LottoTicket> lottoTickets = lottoTicketFactory.createTickets(purchaseAmount);
        lottoTicketsRepository.set(lottoTickets);
        outputView.printPurchasedTickets(lottoTickets);
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
