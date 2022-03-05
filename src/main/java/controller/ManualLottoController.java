package controller;

import domain.Amount;
import domain.LottoNumber;
import domain.LottoResults;
import domain.PurchaseLottoCounts;
import domain.RandomLottoNumbersGenerator;
import domain.Ticket;
import domain.Tickets;
import domain.WinningNumbers;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class ManualLottoController {

    public void run() {
        Amount amount = createAmount();
        int manualTicketsCount = InputView.requestManualTicketCount();
        PurchaseLottoCounts purchaseLottoCounts = new PurchaseLottoCounts(manualTicketsCount, amount);
        
        Tickets tickets = makeTickets(purchaseLottoCounts);
        OutputView.printTicketCount(purchaseLottoCounts);
        OutputView.printTickets(tickets);

        LottoResults results = LottoResults.of(createWinningNumbers(), tickets);
        OutputView.printResult(results);
        OutputView.printYield(amount.getYield(results.getProfit()));
    }

    private Tickets makeTickets(PurchaseLottoCounts purchaseLottoCounts) {
        try {
            Tickets manualTickets = createManualTicket(purchaseLottoCounts.getManualCount());
            manualTickets.addAutoTickets(purchaseLottoCounts.getAutoCount(), new RandomLottoNumbersGenerator());
            return manualTickets;
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return makeTickets(purchaseLottoCounts);
        }
    }

    private Amount createAmount() {
        try {
            return new Amount(InputView.requestAmount());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return createAmount();
        }
    }

    private static void checkCountRange(int maximumTicketCount, int inputCount) {
        if (inputCount < 0 || inputCount > maximumTicketCount) {
            throw new IllegalArgumentException("구매할 로또 수를 다시 입력해주세요.");
        }
    }

    private Tickets createManualTicket(int manualTicketsCount) {
        List<String> manualNumbersInput = InputView.requestManualNumbers(manualTicketsCount);
        return new Tickets(manualNumbersInput.stream()
                .map(Ticket::from)
                .collect(Collectors.toList()));
    }

    private WinningNumbers createWinningNumbers() {
        try {
            Ticket winTicket = createWinTicket();
            LottoNumber bonusNumber = createBonusNumber();
            return new WinningNumbers(winTicket, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return createWinningNumbers();
        }
    }

    private Ticket createWinTicket() {
        try {
            return Ticket.from(InputView.requestWinNumbers());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return createWinTicket();
        }
    }

    private LottoNumber createBonusNumber() {
        try {
            return LottoNumber.valueOf(InputView.requestBonusNumber());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return createBonusNumber();
        }
    }
}
