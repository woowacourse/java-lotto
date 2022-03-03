package domain;

import static java.util.Objects.*;

public class PurchaseCount {

    private final int manualCount;
    private final int autoCount;

    public PurchaseCount(Money money, int manualCount) {
        validateManualCount(money, manualCount);
        this.manualCount = manualCount;
        this.autoCount = calculateTotalCount(money) - manualCount;
    }

    private int calculateTotalCount(Money money) {
        return money.getPurchasableNumber();
    }

    private void validateManualCount(Money money, int manualCount) {
        if (manualCount > money.getPurchasableNumber()) {
            throw new IllegalArgumentException("현재 금액으로 해당 로또 수를 구매할 수 없습니다.");
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseCount that = (PurchaseCount) o;
        return manualCount == that.manualCount && autoCount == that.autoCount;
    }

    @Override
    public int hashCode() {
        return hash(manualCount, autoCount);
    }
}
