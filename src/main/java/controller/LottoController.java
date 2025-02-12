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
        String purchaseAmount = inputView.purchaseAmountInput();
        String winningNumbers = inputView.winningNumbersInput();
        String bonusNumber = inputView.bonusNumberInput();

        System.out.println(purchaseAmount);
        System.out.println(winningNumbers);
        System.out.println(bonusNumber);
    }
}
