package lotto;

import lotto.controller.InputViewController;
import lotto.controller.MoneyController;
import lotto.view.InputView;

public class Main {
    public static void main(String[] args) {
        MoneyController moneyController = new MoneyController();
        InputViewController inputViewController = new InputViewController(new InputView());
        int money = inputViewController.inputMoney();
    }
}



