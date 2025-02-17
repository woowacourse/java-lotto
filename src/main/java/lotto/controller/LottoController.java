package lotto.controller;

import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        outputView.printTicket(lottoService.buyLottos(inputView.readPayment()));
        lottoService.setWinningBalls(inputView.readWinningBalls());
        outputView.printResult(lottoService.getResult());
    }
}
