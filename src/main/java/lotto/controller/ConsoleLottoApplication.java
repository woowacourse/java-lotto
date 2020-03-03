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
        int manualTicketCount = createManualLottoTicketCount();
        TicketCounts ticketCounts = TicketCounts.fromMoneyAndManualTicketCount(purchaseMoney, manualTicketCount);

        LottoTickets lottoTickets = createLottoTickets(ticketCounts);
        OutputView.printLottos(ticketCounts, lottoTickets);

        WinningNumbers winningNumbers = inputWinningNumbers();

        Ranks ranks = winningNumbers.checkOutLottos(lottoTickets);
        Profit profit = ranks.calculateProfit(purchaseMoney);

        OutputView.printRanks(ranks);
        OutputView.printProfit(profit);
    }


    private static Money createPurchaseMoney() {
        try {
            return generateMoneyFromInput();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createPurchaseMoney();
        }
    }

    private static Money generateMoneyFromInput() {
        String inputForPurchaseMoney = InputView.inputPurchaseMoney();
        int valueForPurchaseMoney = Integer.parseInt(inputForPurchaseMoney);
        return Money.ofPurchaseMoney(valueForPurchaseMoney);
    }

    private static int createManualLottoTicketCount() {
        try {
            return InputView.inputManualLottoTicketCount();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createManualLottoTicketCount();
        }
    }

    private static LottoTickets createLottoTickets(TicketCounts ticketCounts) {
        try {
            List<String> manualLottoNumbers = inputManualLottoNumbers(ticketCounts.getManualTicketCount());
            return LottoTickets.of(ticketCounts, manualLottoNumbers);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createLottoTickets(ticketCounts);
        }
    }

    private static List<String> inputManualLottoNumbers(int manualTicketCount) {
        try {
            return InputView.inputManualLottoNumbers(manualTicketCount);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputManualLottoNumbers(manualTicketCount);
        }
    }

    private static WinningNumbers inputWinningNumbers() {
        try {
            return new WinningNumbers(inputWinningLottoTicket(), inputBonusNumber());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private static LottoTicket inputWinningLottoTicket() {
        try {
            return LottoTicket.fromInput(InputView.inputSixNumbers());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputWinningLottoTicket();
        }
    }

    private static LottoNumber inputBonusNumber() {
        try {
            return generateBonusNumberFromInput();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputBonusNumber();
        }
    }

    private static LottoNumber generateBonusNumberFromInput() {
        String inputForBonusNumber = InputView.inputBonusNumber();
        int valueForBonusNumber = Integer.parseInt(inputForBonusNumber);
        return new LottoNumber(valueForBonusNumber);
    }
}
