package lotto.controller;

import lotto.view.InputView;

public class InputViewController {
    private final InputView inputView;

    public InputViewController(InputView inputView) {
        this.inputView = inputView;
    }

    public int inputMoney() {
        return inputView.inputMoney();
    }
}
