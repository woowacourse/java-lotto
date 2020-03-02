package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

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

        Ranks ranks = winningNumbers.checkOutLottos(allLottoTickets);
        Profit profit = ranks.calculateProfit(purchaseMoney);

        OutputView.printRanks(ranks);
        OutputView.printProfit(profit);
    }
}
