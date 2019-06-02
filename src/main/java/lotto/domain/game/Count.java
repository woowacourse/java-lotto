package lotto.domain.game;

import java.util.Objects;

import lotto.domain.PurchaseAmount;

public class Count {
    private final int count;

    public Count(int count) {
        validCount(count);
        this.count = count;
    }

    private void validCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("유효하지 않습니다.");
        }
    }

    public Count(PurchaseAmount purchaseAmount) {
        this(purchaseAmount.ofCount());
    }

    public int subtract(int count) {
        return this.count - count;
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

    public int compareTo(int manualCount) {
        return Integer.compare(count, manualCount);
    }

    public boolean isUnder(int i) {
        return i < count;
    }
}
