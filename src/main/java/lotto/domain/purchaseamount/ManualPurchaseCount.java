package lotto.domain.purchaseamount;

public class ManualPurchaseCount {
    private static final String MANUAL_PURCHASE_AMOUNT_NOT_NATURAL_NUMBER_EXCEPTION_MESSAGE =
            "수동 구매 로또 수는 자연수여야합니다.";
    private static final String MANUAL_PURCHASE_AMOUNT_HIGHER_THAN_TOTAL_AMOUNT_EXCEPTION_MESSAGE =
            "총 구매 수보다 수동 구매 수가 큽니다.";

    private final int value;

    public ManualPurchaseCount(final int value, final int totalPurchaseCount) {
        validateRange(value);
        validateLowerThanTotalPurchaseAmount(value, totalPurchaseCount);
        this.value = value;
    }

    private void validateRange(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException(MANUAL_PURCHASE_AMOUNT_NOT_NATURAL_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private void validateLowerThanTotalPurchaseAmount(final int value, final int totalPurchaseAmount) {
        if (totalPurchaseAmount < value) {
            throw new IllegalArgumentException(MANUAL_PURCHASE_AMOUNT_HIGHER_THAN_TOTAL_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
