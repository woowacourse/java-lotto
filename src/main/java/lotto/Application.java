package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        PurchasingPrice purchasingPrice = new PurchasingPrice(InputView.inputPurchasingPrice());
        List<ManualTicketNumbers> manualTicketsNumbers = InputView.inputManualTicketNumbers();
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberGenerator());
        LottoTickets lottoTickets = lottoMachine.issueLottoTickets(purchasingPrice, manualTicketsNumbers);
        OutputView.printPurchasedLottoTicketCounts(manualTicketsNumbers.size(), lottoTickets.getTicketCounts());
        OutputView.printAllLottoTicketNumbers(lottoTickets);

        List<Integer> winningTicketNumbers = InputView.inputWinningTicketNumbers();
        int bonusBallNumber = InputView.inputBonusBallNumber();
        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(winningTicketNumbers, bonusBallNumber);
        LottoResult lottoResult = lottoTickets.checkResult(winningLottoTicket);
        int purchasingPriceTotal = lottoMachine.calculatePurchasingPrice(lottoTickets);
        double yield = lottoResult.calculateYield(purchasingPriceTotal);
        OutputView.printLottoResult(lottoResult, yield);
    }
}
