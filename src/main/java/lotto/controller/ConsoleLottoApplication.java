package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleLottoApplication {
    private ConsoleLottoApplication() {
    }

    public static void main(String[] args) {
        Money purchaseMoney = inputPurchaseMoney();
        int manualTicketCount = inputManualLottoTicketCount();
        TicketCounts ticketCounts = TicketCounts.fromMoneyAndManualTicketCount(purchaseMoney, manualTicketCount);

        LottoTickets manualLottoTickets = inputManualLottoTickets(manualTicketCount);

        int autoTicketCount = ticketCounts.getAutoTicketCount();
        LottoTickets autoLottoTickets = LottoTickets.ofAutoLottoTickets(autoTicketCount);

        OutputView.printLottos(manualLottoTickets, autoLottoTickets);

        WinningNumbers winningNumbers = inputWinningNumbers();
        LottoTickets allLottoTickets = LottoTickets.join(manualLottoTickets, autoLottoTickets);

        Ranks ranks = winningNumbers.checkOutLottos(allLottoTickets);
        Profit profit = ranks.calculateProfit(purchaseMoney);

        OutputView.printRanks(ranks);
        OutputView.printProfit(profit);
    }

    private static Money inputPurchaseMoney() {
        try {
            return generateMoneyFromInput();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputPurchaseMoney();
        }
    }

    private static Money generateMoneyFromInput() {
        String inputForPurchaseMoney = InputView.inputPurchaseMoney();
        int valueForPurchaseMoney = Integer.parseInt(inputForPurchaseMoney);
        return Money.ofPurchaseMoney(valueForPurchaseMoney);
    }

    private static int inputManualLottoTicketCount() {
        try {
            return InputView.inputManualLottoTicketCount();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputManualLottoTicketCount();
        }
    }

    private static LottoTickets inputManualLottoTickets(int manualTicketCount) {
        try {
            List<String> inputsForNumbers = InputView.inputManualLottoNumbers(manualTicketCount);
            return LottoTickets.ofManualLottoTickets(manualTicketCount, inputsForNumbers);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return inputManualLottoTickets(manualTicketCount);
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
