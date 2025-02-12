package controller;

import dto.TicketAmountResponse;
import model.Ticket;
import utils.Validator;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final OutputView outputView;
    private final InputView inputView;

    public LottoController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printStartMessage();
        Ticket ticket = createTicket();
        printTicketPurchaseAmount(ticket);
    }

    private void printTicketPurchaseAmount(Ticket ticket) {
        TicketAmountResponse ticketAmount = ticket.getTicketAmount();
        outputView.printTicketPurchaseAmount(ticketAmount);
    }

    private Ticket createTicket() {
        int purchaseAmount = enterPrice();
        return new Ticket(purchaseAmount);
    }

    private int enterPrice() {
        String input = inputView.enterPrice();
        Validator.validateNumeric(input);
        return Integer.parseInt(input);
    }
}
