package controller;

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
        String input = inputView.enterPrice();
        Validator.validateNumeric(input);
        int purchaseMoney = Integer.parseInt(input);
        Validator.validateRange(purchaseMoney, Integer.MAX_VALUE, 1000);

    }
}
