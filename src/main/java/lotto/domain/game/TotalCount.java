package lotto.domain.game;

import java.util.Objects;

import lotto.domain.PurchaseAmount;

public class TotalCount {
    private final int count;

    public TotalCount(PurchaseAmount purchaseAmount) {
        this.count = purchaseAmount.getCount();
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalCount that = (TotalCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
