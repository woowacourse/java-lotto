package lotto.model;

public class PurchaseAmount {
    private final int purchaseAmount;
    private static final int ZERO = 0;

    public PurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= ZERO) {
            throw new IllegalArgumentException("잘못된 구입 금액입니다.");
        }
        this.purchaseAmount = purchaseAmount;
    }

    public int calculatePurchaseQuantity(int price) {
        int purchaseQuantity = (this.purchaseAmount / price);
        if (purchaseQuantity < 1) {
            throw new IllegalArgumentException("구매 가능한 개수가 0개 입니다.");
        }
        return purchaseQuantity;
    }
}