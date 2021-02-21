package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    public void run() {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());
        LottoTickets lottoTickets = buyLottoTickets(lottoMachine);
        WinningLottoTicket winningLottoTicket = generateWinningLottoTicket();
        LottoResult lottoResult = lottoTickets.checkResult(winningLottoTicket);
        int purchasingPriceTotal = lottoMachine.calculatePurchasingPrice(lottoTickets);
        double yield = lottoResult.calculateYield(purchasingPriceTotal);
        OutputView.printLottoResult(lottoResult, yield);
    }

    private LottoTickets buyLottoTickets(LottoMachine lottoMachine) {
        PurchasingPrice purchasingPrice = new PurchasingPrice(InputView.inputPurchasingPrice());
        List<ManualTicketNumbers> manualTicketsNumbers = InputView.inputManualTicketsNumbers();
        LottoTickets lottoTickets = lottoMachine.issueLottoTickets(purchasingPrice, manualTicketsNumbers);
        OutputView.printPurchasedLottoTicketCounts(manualTicketsNumbers.size(), lottoTickets.getTicketCounts());
        OutputView.printAllLottoTicketNumbers(lottoTickets);
        return lottoTickets;
    }

    private WinningLottoTicket generateWinningLottoTicket() {
        List<Integer> winningTicketNumbers = InputView.inputWinningTicketNumbers();
        int bonusBallNumber = InputView.inputBonusBallNumber();
        return WinningLottoTicket.of(winningTicketNumbers, bonusBallNumber);
    }
}
