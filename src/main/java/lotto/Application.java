package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        PurchasingPrice purchasingPrice = new PurchasingPrice(InputView.inputPurchasingPrice());
        int lottoTicketCounts = purchasingPrice.calculatePurchasableTicketCounts(1000);
        LottoTickets lottoTickets = LottoTickets.generateAutomatic(lottoTicketCounts, new RandomLottoNumberGenerator());
        OutputView.printPurchasedLottoTicketCounts(lottoTicketCounts);
        OutputView.printAllLottoTicketNumbers(lottoTickets);

        List<Integer> winningTicketNumbers = InputView.inputWinningTicketNumbers();
        int bonusBallNumber = InputView.inputBonusBallNumber();
        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(winningTicketNumbers, bonusBallNumber);
        LottoResult lottoResult = lottoTickets.checkResult(winningLottoTicket);
        OutputView.printLottoResult(lottoResult, purchasingPrice);
    }
}
