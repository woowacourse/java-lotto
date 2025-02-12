package controller;

import domain.Money;
import view.InputValidator;
import view.InputView;

public class LottoController {

    private final InputView inputView;
    private final InputValidator inputValidator;

    public LottoController(InputView inputView, InputValidator inputValidator) {
        this.inputView = inputView;
        this.inputValidator = inputValidator;
    }

    public void start() {
        String rawMoney = inputView.inputMoney();
        inputValidator.validateInputMoney(rawMoney);
        Money money = new Money(rawMoney);
    }
}
