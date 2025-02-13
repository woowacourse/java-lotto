package controller;

import service.parser.MoneyParser;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
         int input = inputLottoMoney();
    }

    private int inputLottoMoney() {
        while (true) {
            try {
                String response = inputView.askForNormal();
                return MoneyParser.parseMoney(response);
            } catch (IllegalArgumentException e) {
                outputView.displayErrorMessage(e.getMessage());
            }
        }
    }
}