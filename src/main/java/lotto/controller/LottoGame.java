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
        int manualTicketCount = InputView.inputManualLottoTicketCount();
        TicketCounts ticketCounts = TicketCounts.fromMoneyAndManualTicketCount(purchaseMoney, manualTicketCount);
        int autoTicketCount = ticketCounts.getAutoTicketCount();

        List<String> manualLottoNumbers = InputView.inputManualLottoNumbers(manualTicketCount);

        LottoTickets manualLottoTickets = LottoTickets.ofManualLottoTickets(manualTicketCount, manualLottoNumbers);
        LottoTickets randomLottoTickets = LottoTickets.ofAutoLottoTickets(autoTicketCount);

        OutputView.printLottos(manualLottoTickets, randomLottoTickets);

        WinningNumbers winningNumbers = InputView.inputWinningNumbers();
        LottoTickets allLottoTickets = LottoTickets.add(manualLottoTickets, randomLottoTickets);

        List<Rank> ranks = winningNumbers.checkOutLottos(allLottoTickets);
        Profit profit = new Profit(purchaseMoney, ranks);

        OutputView.printResult(ranks);
        OutputView.printProfit(profit);
    }
}
