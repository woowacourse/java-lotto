package lotterymachine.domain;

import java.util.Objects;

public class LotteryPurchaseCount {
    private static final String INVALID_PASSIVITY_VALUE = "로또 구매 개수가 총 구매 개수 보다 높습니다.";

    private final int autoValue;
    private final int passivityValue;

    public LotteryPurchaseCount(int passivityValue, int autoValue, int totalValue) {
        validatePassivityValue(passivityValue, autoValue, totalValue);
        this.passivityValue = passivityValue;
        this.autoValue = autoValue;
    }

    public void validatePassivityValue(int passivityValue, int autoValue, int totalValue) {
        if (totalValue < passivityValue + autoValue) {
            throw new IllegalArgumentException(INVALID_PASSIVITY_VALUE);
        }
    }

    public int getAutoValue() {
        return this.autoValue;
    }

    public int getPassivityValue() {
        return this.passivityValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryPurchaseCount lotteryPurchaseCount = (LotteryPurchaseCount) o;
        return autoValue == lotteryPurchaseCount.autoValue && passivityValue == lotteryPurchaseCount.passivityValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoValue, passivityValue);
    }
}
