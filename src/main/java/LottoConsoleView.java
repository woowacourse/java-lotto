public class LottoConsoleView {

    private final InputView inputView;

    public LottoConsoleView(InputView inputView) {
        this.inputView = inputView;
    }

    public int requestPurchaseAmount() {
        String rawPurchaseAmount = inputView.inputPurchaseAmount();
        try {
            int purchaseAmount = Integer.parseInt(rawPurchaseAmount);
            validatePositive(purchaseAmount);
            return purchaseAmount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    private void validatePositive(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양수여합니다.");
        }
    }
}
