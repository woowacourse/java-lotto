package lotto.controller;

import static lotto.controller.ControllerTemplate.supplierTemplate;
import static lotto.view.InputView.inputMoneyText;

import lotto.model.LottoMachine;
import lotto.model.Money;

public class InputMoneyController {
    private final LottoMachine lottoMachine;

    public InputMoneyController(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        Money money = supplierTemplate(this::createMoney, ExceptionHandler::handle);
        lottoMachine.inputMoney(money);
    }

    private Money createMoney() {
        String moneyText = inputMoneyText(ExceptionHandler::handle);
        int amount = Integer.parseInt(moneyText.trim());
        return new Money(amount);
    }
}
