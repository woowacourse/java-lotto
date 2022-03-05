package controller;

import domain.Amount;
import domain.LottoNumber;
import domain.LottoResults;
import domain.ManualLottoNumberGenerator;
import domain.PurchaseLottoCounts;
import domain.RandomLottoNumbersGenerator;
import domain.Ticket;
import domain.Tickets;
import domain.WinningNumbers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import view.InputView;
import view.OutputView;

public class ManualLottoController {

    public void run() {
        Amount amount = createAmount();
        PurchaseLottoCounts purchaseLottoCounts = createPurchaseLottoCounts(amount);

        Tickets tickets = makeTickets(purchaseLottoCounts);
        OutputView.printTicketCount(purchaseLottoCounts);
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

    private PurchaseLottoCounts createPurchaseLottoCounts(Amount amount) {
        try {
            int manualTicketsCount = InputView.requestManualTicketCount();
            return new PurchaseLottoCounts(manualTicketsCount, amount);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return createPurchaseLottoCounts(amount);
        }
    }

    private Tickets makeTickets(PurchaseLottoCounts purchaseLottoCounts) {
        try {
            List<Ticket> manualTickets = createManualTicket(purchaseLottoCounts.getManualCount());
            List<Ticket> autoTickets = createAutoTicket(purchaseLottoCounts.getAutoCount());

            List<Ticket> allTickets = new ArrayList<>();
            allTickets.addAll(manualTickets);
            allTickets.addAll(autoTickets);
            return new Tickets(allTickets);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return makeTickets(purchaseLottoCounts);
        }
    }

    private List<Ticket> createAutoTicket(int autoCount) {
        return IntStream.rangeClosed(1, autoCount)
                .mapToObj(index -> new Ticket(new RandomLottoNumbersGenerator()))
                .collect(Collectors.toList());
    }

    private List<Ticket> createManualTicket(int manualTicketsCount) {
        List<List<Integer>> manualNumbersInput = InputView.requestManualNumbers(manualTicketsCount);
        return manualNumbersInput.stream()
                .map(manualNumbers -> new Ticket(new ManualLottoNumberGenerator(manualNumbers)))
                .collect(Collectors.toList());
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
            return new Ticket(new ManualLottoNumberGenerator(InputView.requestWinNumbers()));
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
