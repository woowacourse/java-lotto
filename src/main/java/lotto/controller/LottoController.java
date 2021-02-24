package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    public void run() {
        PurchasingPrice purchasingPrice = new PurchasingPrice(InputView.inputPurchasingPrice());
        LottoTickets lottoTickets = buyLottoTickets(purchasingPrice);

        List<Integer> winningTicketNumbers = InputView.inputWinningTicketNumbers();
        int bonusBallNumber = InputView.inputBonusBallNumber();
        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(winningTicketNumbers, bonusBallNumber);

        LottoStatistics lottoStatistics = lottoTickets.getStatistics(winningLottoTicket);
        OutputView.printLottoResult(lottoStatistics, purchasingPrice);
    }

    private LottoTickets buyLottoTickets(PurchasingPrice purchasingPrice) {
        int manualTicketCounts = InputView.inputManualTicketCounts();
        PurchasingCounts purchasingCounts = PurchasingCounts.of(purchasingPrice, manualTicketCounts);
        List<List<Integer>> manualLottoNumbers = InputView.inputManualLottoNumbers(manualTicketCounts);
        LottoMachine lottoMachine = new LottoMachine(new ManualLottoNumberGenerator(manualLottoNumbers),
                new RandomLottoNumberGenerator());
        LottoTickets lottoTickets = lottoMachine.buyLottoTickets(purchasingCounts);
        OutputView.printLottoCountMessage(purchasingCounts);
        OutputView.printLottoTicketNumbers(lottoTickets);
        return lottoTickets;
    }
}
