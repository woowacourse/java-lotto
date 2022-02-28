package controller;

import domain.Amount;
import domain.LottoNumber;
import domain.LottoResults;
import domain.RandomLottoNumbersGenerator;
import domain.Ticket;
import domain.Tickets;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

public class AutoLottoController implements Controller {

    @Override
    public void run() {
        Amount amount = createAmount();
        OutputView.printTicketCount(amount.getTicketCount());
        Tickets tickets = createTickets(amount);
        OutputView.printTickets(tickets);
        WinningNumbers winningNumbers = createWinningNumbers();
        LottoResults results = LottoResults.of(winningNumbers, tickets);
        OutputView.printResult(results);
        OutputView.printYield(amount.getYield(results.getProfit()));
    }

    private Amount createAmount() {
        try {
            return new Amount(InputView.requestAmount());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return createAmount();
        }
    }

    private Tickets createTickets(Amount amount) {
        return Tickets.of(amount.getTicketCount(), new RandomLottoNumbersGenerator());
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
