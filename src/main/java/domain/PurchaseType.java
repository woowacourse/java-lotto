package domain;

import java.util.Objects;

public class PurchaseType {

    private final int manualCount;
    private final int automaticCount;

    public PurchaseType(Money money, int manualCount) {
        validateManualCount(money, manualCount);
        this.manualCount = manualCount;
        this.automaticCount = calculateTotalCount(money) - manualCount;
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

    public int getAutomaticCount() {
        return automaticCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseType that = (PurchaseType) o;
        return manualCount == that.manualCount && automaticCount == that.automaticCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualCount, automaticCount);
    }
}
