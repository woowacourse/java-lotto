public class LottoConsoleView {

    private final InputView inputView;

    public LottoConsoleView(InputView inputView) {
        this.inputView = inputView;
    }

    public int requestPurchaseAmount() {
        String rawPurchaseAmount = inputView.inputPurchaseAmount();
        NumberValidator.validateInteger(rawPurchaseAmount);
        int purchaseAmount = Integer.parseInt(rawPurchaseAmount);
        NumberValidator.validatePositive(purchaseAmount);
        return purchaseAmount;
    }
}
