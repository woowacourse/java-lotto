package lotto.service;

import lotto.vaildator.InputValidator;
import lotto.view.InputView;

public class InputService {

    private final InputView inputView;

    public InputService(InputView inputView) {
        this.inputView = inputView;
    }

    public int readPurchaseAmount() {
        String content = inputView.readLine();
        InputValidator.validateBlank(content);
        InputValidator.validateNumberFormat(content);
        int purchaseAmount = Integer.parseInt(content);
        InputValidator.validatePurchaseAmount(purchaseAmount);
        return purchaseAmount;
    }
}
