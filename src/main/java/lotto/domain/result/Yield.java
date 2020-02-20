package lotto.domain.result;

import lotto.domain.number.PurchaseNumber;

import java.util.Objects;

public class Yield {
    private static final int MULTIPLE_PERCENTAGE = 100;

    private final double yield;

    public Yield(double yield) {
        this.yield = yield;
    }

    public static Yield calculateYield(PurchaseNumber purchaseNumber, GameResults gameResults) {
        double purchaseMoney = purchaseNumber.getPurchaseMoney();
        double benefit = gameResults.calculateBenefit();
        return new Yield((benefit / purchaseMoney) * MULTIPLE_PERCENTAGE);
    }

    public double getYield() {
        return yield;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Yield yield1 = (Yield) o;
        return yield == yield1.yield;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yield);
    }
}
