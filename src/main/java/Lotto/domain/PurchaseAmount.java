package Lotto.domain;

public class PurchaseAmount implements Money{
    private static final int LOTTO_PRICE = 1_000;

    private int purchaseAmount;

    public PurchaseAmount(int purchaseAmount) {
        validate(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public void validate(int purchaseAmount) {
        if(isLessThanMinPrice(purchaseAmount)) {
            throw new IllegalArgumentException("구입 금액은 "+ LOTTO_PRICE +"보다 커야합니다.");
        }
    }

    private boolean isLessThanMinPrice(int purchaseAmount) {
        return purchaseAmount <= LOTTO_PRICE;
    }

    public int calculateLottoAmount(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;
    }

    public int getPurchaseAmount() {
        return this.purchaseAmount;
    }
}
