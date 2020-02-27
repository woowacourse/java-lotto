package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoGame {
    private LottoGame() {
    }

    public static void main(String[] args) {
        Money purchaseMoney = InputView.inputPurchaseMoney();
        int allLottoTicketCount = purchaseMoney.calculateQuotient(Money.ofTicketPrice());
        int manualLottoTicketCount = InputView.inputManualLottoTicketCount();
        List<String> manualLottoNumbers = InputView.inputManualLottoNumbers(manualLottoTicketCount);

        LottoTickets manualLottoTickets = LottoTickets.ofManualLottoTickets(manualLottoTicketCount, manualLottoNumbers);
        LottoTickets randomLottoTickets = LottoTickets.ofRandomLottoTickets(allLottoTicketCount - manualLottoTicketCount);

        OutputView.printLottos(manualLottoTickets, randomLottoTickets);

        WinningNumbers winningNumbers = InputView.inputWinningNumbers();
        LottoTickets allLottoTickets = LottoTickets.add(manualLottoTickets, randomLottoTickets);

        List<Rank> ranks = winningNumbers.checkOutLottos(allLottoTickets);
        Profit profit = new Profit(purchaseMoney, ranks);

        OutputView.printResult(ranks);
        OutputView.printProfit(profit);
    }
}
