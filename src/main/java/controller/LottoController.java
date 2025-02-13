package controller;

import dto.TicketAmountResponse;
import model.LottoResult;
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

    public void run() { // todo : 메서드 분리 작업
        Ticket ticket = createTicket();
        printTicketPurchaseAmount(ticket);

        Lottos lottos = new Lottos(ticket.getTicketAmount());
        outputView.printLottos(lottos.createResponse());

        String input = inputView.enterWinningNumbers();
        String bonusNumber = inputView.enterBonusNumber();
        WinningLotto winningLotto = new WinningLotto(input, bonusNumber);

        LottoResult lottoResult = new LottoResult(lottos, winningLotto);

        outputView.printLottoResult(lottoResult.createResponse());
        int totalPrice = lottoResult.calculateTotalPrice();
        outputView.printROIResult(ticket.createROIResponse(totalPrice));
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
