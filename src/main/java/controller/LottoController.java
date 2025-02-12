package controller;

import view.InputView;
import view.OutputView;

public class LottoController {

    private InputView inputView;
    private OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView=outputView;
    }

    public void start() {
        inputView.amountInputMessage();
        String amountInput = inputView.getAmountInput();
    }
}
