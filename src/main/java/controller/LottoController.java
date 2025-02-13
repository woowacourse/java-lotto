package controller;

import domain.Ticket;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void start() {
        int purchaseAmount = inputView.purchaseAmountInput();
        Ticket ticket = lottoService.createTicket(purchaseAmount);
        outputView.printPurchaseResult(ticket);



        String winningNumbers = inputView.winningNumbersInput();
        String bonusNumber = inputView.bonusNumberInput();

    }
}
