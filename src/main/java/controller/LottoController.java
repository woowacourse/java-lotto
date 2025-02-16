package controller;

import dto.TicketAmountResponse;
import model.utils.Validator;
import model.LottoResult;
import model.Lottos;
import model.Ticket;
import model.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final OutputView outputView;
    private final InputView inputView;

    public LottoController(final OutputView outputView, final InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        Ticket ticket = createTicket();
        printTicketPurchaseAmount(ticket);

        Lottos lottos = new Lottos(ticket.getTicketAmount());
        outputView.printLottos(lottos.createResponse());

        WinningLotto winningLotto = enterWinningAndBonusNumber();
        LottoResult lottoResult = new LottoResult(lottos, winningLotto);

        printLottoResult(lottoResult, ticket);
    }

    private void printTicketPurchaseAmount(final Ticket ticket) {
        TicketAmountResponse ticketAmount = ticket.createTicketResponse();
        outputView.printTicketPurchaseAmount(ticketAmount);
    }

    private Ticket createTicket() {
        int purchaseAmount = enterPrice();
        return new Ticket(purchaseAmount);
    }

    private int enterPrice() {
        try {
            String input = inputView.enterPurchasePrice();
            Validator.validateNumeric(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return enterPrice();
        }
    }

    private WinningLotto enterWinningAndBonusNumber() {
        try {
            String winningNumbers = inputView.enterWinningNumbers();
            String bonusNumber = inputView.enterBonusNumber();
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return enterWinningAndBonusNumber();
        }
    }

    private void printLottoResult(final LottoResult lottoResult, final Ticket ticket) {
        outputView.printLottoResult(lottoResult.createResponse());
        int totalPrice = lottoResult.calculateTotalPrice();
        outputView.printReturnOfInvestmentResult(ticket.createReturnOfInvestmentResponse(totalPrice));
    }
}
