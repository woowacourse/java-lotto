package controller;

import view.InputView;

public class Controller {
    private final InputView inputView;

    public Controller(InputView inputView) {
        this.inputView = inputView;

    }

    public void start() {
         String input = inputLottoMoney();
    }

    private String inputLottoMoney() {
        while (true) {
            try {
                return inputView.askForNormal();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}