package lotto.domain;

import java.util.Objects;

import lotto.exceptions.InvalidPurchaseAmountException;

public class PurchaseAmount {
    private static final int MINIMUM_PURCHASE_AMOUNT = 1000;
    private static final String INVALID_PURCHASE_AMOUNT = "구매 금액은 1000원 이상입니다.";

    private final int purchaseAmount;

    private PurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < MINIMUM_PURCHASE_AMOUNT) {
            throw new InvalidPurchaseAmountException(INVALID_PURCHASE_AMOUNT);
        }
        this.purchaseAmount = purchaseAmount;
    }

    public static PurchaseAmount is(int purchaseAmount) {
        return new PurchaseAmount(purchaseAmount);
    }

    public GameCounts getGameCounts() {
        return new GameCounts(purchaseAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseAmount that = (PurchaseAmount) o;
        return purchaseAmount == that.purchaseAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseAmount);
    }
}
