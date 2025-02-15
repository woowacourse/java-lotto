package lotto.domain;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;
    private final long amount;

    public PurchaseAmount(long purchaseAmount) {
        validateAmount(purchaseAmount);
        this.amount = purchaseAmount;
    }

    public long getAmount() {
        return amount;
    }

    public int countNumberOfPurchases() {
        return (int) amount / LOTTO_PRICE;
    }

    private void validateAmount(long purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 " + LOTTO_PRICE + "원 단위어야 합니다.");
        }
    }
}
