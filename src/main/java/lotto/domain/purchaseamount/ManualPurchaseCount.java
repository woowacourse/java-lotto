package lotto.domain.purchaseamount;

public class ManualPurchaseCount {
    private static final String MANUAL_PURCHASE_COUNT_RANGE_EXCEPTION_MESSAGE =
            "수동 구매 개수는 0 이상, 전체 구매 개수 사이의 정수여야 합니다.";

    private final int value;

    public ManualPurchaseCount(final int value, final int totalPurchaseCount) {
        validateRange(value, totalPurchaseCount);
        this.value = value;
    }

    private void validateRange(final int value, final int totalPurchaseCount) {
        if (value < 0 || totalPurchaseCount < value) {
            throw new IllegalArgumentException(MANUAL_PURCHASE_COUNT_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
