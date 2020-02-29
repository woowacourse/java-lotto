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

        LottoTickets manualLottoTickets = InputView.inputManualLottoTickets(manualTicketCount);

        int autoTicketCount = ticketCounts.getAutoTicketCount();
        LottoTickets autoLottoTickets = LottoTickets.ofAutoLottoTickets(autoTicketCount);

        OutputView.printLottos(manualLottoTickets, autoLottoTickets);

        WinningNumbers winningNumbers = InputView.inputWinningNumbers();
        LottoTickets allLottoTickets = LottoTickets.join(manualLottoTickets, autoLottoTickets);

        List<Rank> ranks = winningNumbers.checkOutLottos(allLottoTickets);
        Profit profit = new Profit(purchaseMoney, ranks);

        OutputView.printResult(ranks);
        OutputView.printProfit(profit);
    }
}
