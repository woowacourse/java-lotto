package controller;

import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {

    }
}
