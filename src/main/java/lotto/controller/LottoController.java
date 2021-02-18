package lotto.controller;

import java.util.Scanner;
import lotto.domain.lotto.Money;
import lotto.utils.InputValidationUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(Scanner scanner) {
        this.inputView = new InputView(scanner);
    }

    public void play() {
        Money purchaseAmount = inputPurchaseAmount();
    }

    private Money inputPurchaseAmount() {
        try {
            OutputView.printPurchaseAmountGuideMessage();
            int purchaseAmountValue = Integer.parseInt(inputView.inputPurchaseAmount());
            InputValidationUtils.validatePurchaseAmount(purchaseAmountValue);
            return Money.priceOf(purchaseAmountValue);
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmount();
        }
    }
}


