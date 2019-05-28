package lotto.domain;

import java.util.Objects;

import lotto.exceptions.InvalidPurchaseAmountException;

public class PurchaseAmount {
    private static final int PURCHASE_AMOUNT_UNIT = 1000;
    private static final int PROPER_REMINDER = 0;
    private static final String INVALID_PURCHASE_AMOUNT = "구매 금액은 1000원 이상입니다.";
    private static final int PERCENT = 100;

    private final int purchaseAmount;

    private PurchaseAmount(String input) {
        int purchaseAmount = Integer.valueOf(input);
        if (purchaseAmount < PURCHASE_AMOUNT_UNIT) {
            throw new InvalidPurchaseAmountException(INVALID_PURCHASE_AMOUNT);
        }
        this.purchaseAmount = purchaseAmount;
    }

    public static PurchaseAmount of(String purchaseAmount) {
        return new PurchaseAmount(purchaseAmount);
    }

    public double rateOf(int prizeAmount) {
        return (double) (prizeAmount / purchaseAmount) * PERCENT;
    }

    public boolean hasReminder() {
        return purchaseAmount % PURCHASE_AMOUNT_UNIT == PROPER_REMINDER;
    }

    public int getGameCount() {
        return this.purchaseAmount / PURCHASE_AMOUNT_UNIT;
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
