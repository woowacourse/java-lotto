package domain;

import static java.util.Objects.*;

public class PurchaseCount {

    private final int manualCount;
    private final int autoCount;

    public PurchaseCount(int manualCount, int autoCount) {
        validateNegative(manualCount);
        validateNegative(autoCount);
        this.manualCount = manualCount;
        this.autoCount = autoCount;
    }

    public static PurchaseCount from(Money money, int manualCount) {
        requireNonNull(money, "입력 값이 null일 수 없습니다.");
        validateManualCount(money, manualCount);
        return new PurchaseCount(manualCount, calculateTotalCount(money) - manualCount);
    }

    private static int calculateTotalCount(Money money) {
        return money.getPurchasableNumber();
    }

    private static void validateManualCount(Money money, int manualCount) {
        if (manualCount > money.getPurchasableNumber()) {
            throw new IllegalArgumentException("현재 금액으로 해당 로또 수를 구매할 수 없습니다.");
        }
    }

    private void validateNegative(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("횟수가 음수일 수 없습니다.");
        }
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

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
