package lotto.controller;

import java.util.List;
import lotto.domain.LottoRank;
import lotto.domain.LottoTicketFactory;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningStats;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.view.InputView;
import lotto.view.InputView.IndividualInput;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView = InputView.INSTANCE;
    private final OutputView outputView = OutputView.INSTANCE;
    private final LottoTicketFactory lottoTicketFactory = LottoTicketFactory.INSTANCE;

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();

        List<LottoTicket> lottoTickets = purchaseLottoTickets(purchaseAmount);
        outputView.printPurchasedTickets(lottoTickets);

        WinningNumbers winningNumbers = inputWinningNumbers();

        WinningStats winningStats = calculateStatistics(lottoTickets, winningNumbers);
        outputView.printWinningStats(winningStats, purchaseAmount);
    }

    private PurchaseAmount getPurchaseAmount() {
        IndividualInput<PurchaseAmount> individualInputs =
                () -> new PurchaseAmount(inputView.inputMoney());
        return inputView.commonInputProcess(individualInputs);
    }

    private List<LottoTicket> purchaseLottoTickets(PurchaseAmount purchaseAmount) {
        return lottoTicketFactory.createTickets(purchaseAmount);
    }

    private WinningNumbers inputWinningNumbers() {
        return inputView.commonInputProcess(
                () -> new WinningNumbers(inputView.inputWinningNumbers(), inputView.inputBonusBall()));
    }

    private WinningStats calculateStatistics(List<LottoTicket> lottoTickets, WinningNumbers winningNumbers) {
        WinningStats winningStats = new WinningStats(lottoTickets, winningNumbers);
        return winningStats;
    }


}
