package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    public void run() {
        PurchasingPrice purchasingPrice = new PurchasingPrice(InputView.inputPurchasingPrice());
        LottoTickets allLottoTickets = buyLottoTickets(purchasingPrice);

        List<Integer> winningTicketNumbers = InputView.inputWinningTicketNumbers();
        int bonusBallNumber = InputView.inputBonusBallNumber();
        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(winningTicketNumbers, bonusBallNumber);

        LottoStatistics lottoStatistics = allLottoTickets.getStatistics(winningLottoTicket);
        OutputView.printLottoResult(lottoStatistics, purchasingPrice);
    }

    private LottoTickets buyLottoTickets(PurchasingPrice purchasingPrice) {
        int manualTicketCounts = InputView.inputManualTicketCounts();
        PurchasingCounts purchasingCounts = PurchasingCounts.of(purchasingPrice, manualTicketCounts);
        List<List<Integer>> manualLottoNumbers = InputView.inputManualLottoNumbers(manualTicketCounts);
        LottoTickets allLottoTickets = LottoTickets.generateLottoTickets(purchasingCounts,
                new ManualLottoNumberGenerator(manualLottoNumbers),
                new RandomLottoNumberGenerator());
        OutputView.printLottoCountMessage(purchasingCounts);
        OutputView.printLottoTicketNumbers(allLottoTickets);
        return allLottoTickets;
    }
}
