package lotterymachine.domain;

import java.util.Objects;

public class LotteryPurchaseCount {
    private static final String INVALID_MANUAL_VALUE = "로또 구매 개수가 총 구매 개수 보다 높습니다.";

    private final int autoValue;
    private final int manualValue;

    public LotteryPurchaseCount(int manualValue, int autoValue, int totalValue) {
        validateManualValue(manualValue, autoValue, totalValue);
        this.manualValue = manualValue;
        this.autoValue = autoValue;
    }

    public void validateManualValue(int manualValue, int autoValue, int totalValue) {
        if (totalValue < manualValue + autoValue) {
            throw new IllegalArgumentException(INVALID_MANUAL_VALUE);
        }
    }

    public int getAutoValue() {
        return this.autoValue;
    }

    public int getManualValue() {
        return this.manualValue;
    }

    public int getTotalValue() {
        return autoValue + manualValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryPurchaseCount lotteryPurchaseCount = (LotteryPurchaseCount) o;
        return autoValue == lotteryPurchaseCount.autoValue && manualValue == lotteryPurchaseCount.manualValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoValue, manualValue);
    }
}
