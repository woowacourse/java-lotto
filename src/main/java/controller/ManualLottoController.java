package controller;

import domain.Amount;
import domain.LottoNumber;
import domain.LottoResults;
import domain.RandomLottoNumbersGenerator;
import domain.Ticket;
import domain.Tickets;
import domain.WinningNumbers;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class ManualLottoController implements Controller {

    @Override
    public void run() {
        Amount amount = createAmount();
        int manualTicketsCount = createManualTicketCount(amount);
        int autoTicketCount = amount.getTicketCount() - manualTicketsCount;
        Tickets tickets = makeTickets(manualTicketsCount, autoTicketCount);
        OutputView.printTicketCount(manualTicketsCount, autoTicketCount);
        OutputView.printTickets(tickets);
        WinningNumbers winningNumbers = createWinningNumbers();
        LottoResults results = LottoResults.of(winningNumbers, tickets);
        OutputView.printResult(results);
        OutputView.printYield(amount.getYield(results.getProfit()));
    }

    private Tickets makeTickets(int manualTicketsCount, int autoTicketCount) {
        Tickets manualTickets = createManualTicket(manualTicketsCount);
        Tickets autoTickets = Tickets.of(autoTicketCount, new RandomLottoNumbersGenerator());
        manualTickets.add(autoTickets);
        return manualTickets;
    }

    private Amount createAmount() {
        try {
            return new Amount(InputView.requestAmount());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return createAmount();
        }
    }

    private int createManualTicketCount(Amount amount) {
        try {
            return InputView.requestAutoTicketCount(amount.getTicketCount());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return createManualTicketCount(amount);
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
            return new LottoNumber(InputView.requestBonusNumber());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return createBonusNumber();
        }
    }
}
