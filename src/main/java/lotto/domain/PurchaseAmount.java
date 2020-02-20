package lotto.domain;

public class PurchaseAmount {
    private static final int MINIMUM_MONEY = 1000;
    private static final int LOTTO_PRICE = 1000;
    private static final String INVALID_PURCHASE_MONEY_EXCEPTION_MESSAGE = "Input money out of range (minimum - 1000).";

    private final int purchaseAmount;

    private PurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public static PurchaseAmount calculate(int purchaseMoney) {
        validatePurchaseMoney(purchaseMoney);
        return new PurchaseAmount(purchaseMoney / LOTTO_PRICE);
    }

    private static void validatePurchaseMoney(int purchaseMoney) {
        if (purchaseMoney < MINIMUM_MONEY) {
            throw new IllegalArgumentException(INVALID_PURCHASE_MONEY_EXCEPTION_MESSAGE);
        }
    }

    public int getPurchaseNumber() {
        return purchaseAmount;
    }
}