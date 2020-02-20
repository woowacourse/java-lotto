package lotto.domain.result;

import lotto.domain.number.PurchaseNumber;

import java.util.Objects;

public class Yield {

    private final long yield;

    public Yield(long yield) {
        this.yield = yield;
    }

    public static Yield calculateYield(PurchaseNumber purchaseNumber, GameResults gameResults) {
        int purchaseMoney = purchaseNumber.getPurchaseMoney();
        long benefit = gameResults.calculateBenefit();
        return new Yield((benefit / purchaseMoney) * 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Yield yield1 = (Yield) o;
        return yield == yield1.yield;
    }

    public long getYield() {
        return yield;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yield);
    }
}
