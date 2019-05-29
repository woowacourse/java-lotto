package lotto.domain;

import java.util.Objects;

public class TotalCount {
    private final int totalCount;

    public TotalCount(PurchaseAmount purchaseAmount) {
        this.totalCount = purchaseAmount.getCount();
    }

    public int getTotalCount() {
        return totalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalCount that = (TotalCount) o;
        return totalCount == that.totalCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCount);
    }
}
