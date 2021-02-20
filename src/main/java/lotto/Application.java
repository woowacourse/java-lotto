package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        PurchasingPrice purchasingPrice = new PurchasingPrice(InputView.inputPurchasingPrice());
        LottoTickets lottoTickets = LottoTickets.generateAutomatic(purchasingPrice, new RandomLottoNumberGenerator());
        OutputView.printPurchasedLottoTicketCounts(lottoTickets.getTicketCounts());
        OutputView.printAllLottoTicketNumbers(lottoTickets);

        List<Integer> winningTicketNumbers = InputView.inputWinningTicketNumbers();
        int bonusBallNumber = InputView.inputBonusBallNumber();
        WinningLottoTicket winningLottoTicket = WinningLottoTicket.of(winningTicketNumbers, bonusBallNumber);
        LottoResult lottoResult = lottoTickets.checkResult(winningLottoTicket);
        double yield = lottoResult.calculateYield(lottoTickets.getPurchasingPrice());
        OutputView.printLottoResult(lottoResult, yield);
    }
}
