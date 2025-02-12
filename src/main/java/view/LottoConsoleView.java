package view;

import common.NumberValidator;

public class LottoConsoleView {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoConsoleView(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int requestPurchaseAmount() {
        String rawPurchaseAmount = inputView.inputPurchaseAmount();
        NumberValidator.validateInteger(rawPurchaseAmount);
        int purchaseAmount = Integer.parseInt(rawPurchaseAmount);
        NumberValidator.validatePositive(purchaseAmount);
        return purchaseAmount;
    }

    public void printPurchaseCount(int purchaseCount) {
        outputView.printPurchaseCount(purchaseCount);
    }

}
