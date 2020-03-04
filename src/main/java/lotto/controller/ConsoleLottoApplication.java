package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleLottoApplication {
    private ConsoleLottoApplication() {
    }

    public static void main(String[] args) {
        Money purchaseMoney = createPurchaseMoney();
        int manualTicketCount = inputManualTicketCount();
        TicketCounts ticketCounts = TicketCounts.from(purchaseMoney, manualTicketCount);

        LottoTickets lottoTickets = createLottoTickets(ticketCounts);
        OutputView.printLottos(ticketCounts, lottoTickets);

        WinningNumbers winningNumbers = createWinningNumbers();

        Ranks ranks = winningNumbers.checkOutLottos(lottoTickets);
        Profit profit = ranks.calculateProfit(purchaseMoney);

        OutputView.printRanks(ranks);
        OutputView.printProfit(profit);
    }

    private static Money createPurchaseMoney() {
        try {
            int inputForPurchaseMoney = Integer.parseInt(InputView.inputPurchaseMoney());
            return Money.createPurchaseMoney(inputForPurchaseMoney);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createPurchaseMoney();
        }
    }

    private static int inputManualTicketCount() {
        try {
            return Integer.parseInt(InputView.inputManualTicketCount());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputManualTicketCount();
        }
    }

    private static LottoTickets createLottoTickets(TicketCounts ticketCounts) {
        try {
            List<String> manualLottoNumbers = InputView.inputManualLottoNumbers(ticketCounts.getManualTicketCount());
            return LottoTickets.from(ticketCounts, manualLottoNumbers);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createLottoTickets(ticketCounts);
        }
    }

    private static WinningNumbers createWinningNumbers() {
        try {
            return new WinningNumbers(inputWinningLottoTicket(), inputBonusNumber());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createWinningNumbers();
        }
    }

    private static LottoTicket inputWinningLottoTicket() {
        try {
            return LottoTicket.createManualTicket(InputView.inputWinningLottoTicket());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputWinningLottoTicket();
        }
    }

    private static LottoNumber inputBonusNumber() {
        try {
            int inputForBonusNumber = Integer.parseInt(InputView.inputBonusNumber());
            return LottoNumber.valueOf(inputForBonusNumber);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputBonusNumber();
        }
    }
}
