package lotto.domain;

import java.util.Objects;

import lotto.exceptions.PurchaseAmountException;

public class PurchaseAmount {
    private static final String INVALID_PURCHASE_AMOUNT = "구매 금액은 1000원 이상입니다.";
    private static final String INVALID_PURCHASE_UNIT = "구매 단위는 1000원입니다.";
    private static final int PURCHASE_AMOUNT_UNIT = 1000;
    private static final int PROPER_REMINDER = 0;
    private static final int PERCENT = 100;

    private final int purchaseAmount;

    private PurchaseAmount(String input) {
        int purchaseAmount = Integer.valueOf(input);
        validAmount(purchaseAmount);
        validUnit(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public static PurchaseAmount of(String purchaseAmount) {
        return new PurchaseAmount(purchaseAmount);
    }

    private void validAmount(int purchaseAmount) {
        if (purchaseAmount < PURCHASE_AMOUNT_UNIT) {
            throw new PurchaseAmountException(INVALID_PURCHASE_AMOUNT);
        }
    }

    private void validUnit(int purchaseAmount) {
        if (!isProperUnit(purchaseAmount)) {
            throw new PurchaseAmountException(INVALID_PURCHASE_UNIT);
        }
    }

    private boolean isProperUnit(int purchaseAmount) {
        return purchaseAmount % PURCHASE_AMOUNT_UNIT == PROPER_REMINDER;
    }

    public double rateOf(double prizeAmount) {
        return (prizeAmount / purchaseAmount) * PERCENT;
    }

    public int getCount() {
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
