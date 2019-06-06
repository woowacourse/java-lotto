package lotto.domain;

import java.util.Objects;

import lotto.exceptions.PurchaseAmountException;
import lotto.exceptions.PurchaseUnitException;

public class PurchaseAmount {
    private static final int PURCHASE_AMOUNT_UNIT = 1000;
    private static final int PROPER_REMINDER = 0;
    private static final int PERCENT = 100;

    private final int purchaseAmount;

    private PurchaseAmount(int purchaseAmount) {
        validAmount(purchaseAmount);
        validUnit(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public static PurchaseAmount of(int purchaseAmount) {
        return new PurchaseAmount(purchaseAmount);
    }

    private void validAmount(int purchaseAmount) {
        if (purchaseAmount < PURCHASE_AMOUNT_UNIT) {
            throw new PurchaseAmountException();
        }
    }

    private void validUnit(int purchaseAmount) {
        if (!isProperUnit(purchaseAmount)) {
            throw new   PurchaseUnitException();
        }
    }

    private boolean isProperUnit(int purchaseAmount) {
        return purchaseAmount % PURCHASE_AMOUNT_UNIT == PROPER_REMINDER;
    }

    public double rateOf(double prizeAmount) {
        return ((prizeAmount - purchaseAmount) / purchaseAmount) * PERCENT;
    }

    public int ofCount() {
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
