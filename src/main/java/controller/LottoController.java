package controller;

import java.io.IOException;
import model.Money;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        Money inputMoney = inputMoney();
    }

    private Money inputMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }
}
