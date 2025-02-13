package controller;

import domain.Lotto;
import domain.Ticket;
import domain.WinningNumber;
import java.util.List;
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
        lottoService.createLottos(ticket);

        List<Lotto> lottos = lottoService.getLottos();
        outputView.printLottos(lottos);

        String winningNumbers = inputView.winningNumbersInput();
        Lotto lotto = lottoService.createLotto(winningNumbers);

        int bonusNumber = inputView.bonusNumberInput();
        WinningNumber winningNumber = lottoService.createWinningNumber(lotto, bonusNumber);

        System.out.println(winningNumber.getNumbers());
        System.out.println(winningNumber.getBonusNumber());

    }
}
