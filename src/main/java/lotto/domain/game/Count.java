package lotto.domain.game;

import java.util.Objects;

import lotto.domain.PurchaseAmount;

public class Count {
    private final int count;

    public Count(int count) {
        this.count = count;
    }

    public Count(PurchaseAmount purchaseAmount) {
        this(purchaseAmount.getCount());
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count that = (Count) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
