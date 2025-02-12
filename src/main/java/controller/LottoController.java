package controller;

import dto.TicketAmountResponse;
import model.Lottos;
import model.Ticket;
import model.WinningLotto;
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
        Ticket ticket = createTicket();
        printTicketPurchaseAmount(ticket);

        Lottos lottos = new Lottos(ticket.getTicketAmount());
        outputView.printLottos(lottos.createResponse());

        String input = inputView.enterWinningNumbers();
        String bonusNumber = inputView.enterBonusNumber();
        WinningLotto winningLotto = new WinningLotto(input, bonusNumber);
    }

    private void printTicketPurchaseAmount(Ticket ticket) {
        TicketAmountResponse ticketAmount = ticket.createResponse();
        outputView.printTicketPurchaseAmount(ticketAmount);
    }

    private Ticket createTicket() {
        int purchaseAmount = enterPrice();
        return new Ticket(purchaseAmount);
    }

    private int enterPrice() {
        String input = inputView.enterPurchasePrice();
        Validator.validateNumeric(input);
        return Integer.parseInt(input);
    }

}
