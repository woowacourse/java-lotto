package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.inputPurchasingPrice());
        LottoTickets lottoTickets = buyLottoTickets(money);

        List<Integer> winningTicketNumbers = InputView.inputWinningTicketNumbers();
        int bonusBallNumber = InputView.inputBonusBallNumber();
        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(winningTicketNumbers, bonusBallNumber);

        LottoStatistics lottoStatistics = lottoTickets.getStatistics(winningLottoTicket);
        OutputView.printLottoResult(lottoStatistics, money);
    }

    private LottoTickets buyLottoTickets(Money money) {
        int manualTicketCounts = InputView.inputManualTicketCounts();
        PurchasingCounts purchasingCounts = PurchasingCounts.of(money, manualTicketCounts);
        List<List<Integer>> manualLottoNumbers = InputView.inputManualLottoNumbers(manualTicketCounts);
        LottoMachine lottoMachine = new LottoMachine(new ManualLottoNumberGenerator(manualLottoNumbers),
                new RandomLottoNumberGenerator());
        LottoTickets lottoTickets = lottoMachine.buyLottoTickets(purchasingCounts);
        OutputView.printLottoCountMessage(purchasingCounts);
        OutputView.printLottoTicketNumbers(lottoTickets);
        return lottoTickets;
    }
}
