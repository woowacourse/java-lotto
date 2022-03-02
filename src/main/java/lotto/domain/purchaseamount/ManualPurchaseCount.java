package lotto.domain.purchaseamount;

import java.util.regex.Pattern;

public class ManualPurchaseCount {
    private static final Pattern NATURAL_NUMBER_REGEX = Pattern.compile("^[0-9][0-9]*$");
    private static final String MANUAL_PURCHASE_AMOUNT_NOT_NATURAL_NUMBER_EXCEPTION_MESSAGE =
            "수동 구매 로또 수는 자연수여야합니다.";
    private static final String MANUAL_PURCHASE_AMOUNT_HIGHER_THAN_TOTAL_AMOUNT_EXCEPTION_MESSAGE =
            "총 구매 수보다 수동 구매 수가 큽니다.";

    private final int value;

    public ManualPurchaseCount(final String value, final PurchaseAmount purchaseAmount) {
        validateNaturalNumber(value);
        int numberValue = Integer.parseInt(value);
        validateLowerThanTotalPurchaseAmount(numberValue, purchaseAmount);
        this.value = numberValue;
    }

    private void validateNaturalNumber(final String value) {
        if (!NATURAL_NUMBER_REGEX.matcher(value).matches()) {
            throw new IllegalArgumentException(MANUAL_PURCHASE_AMOUNT_NOT_NATURAL_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private void validateLowerThanTotalPurchaseAmount(final int value, final PurchaseAmount purchaseAmount) {
        if (purchaseAmount.isLowerThan(value)) {
            throw new IllegalArgumentException(MANUAL_PURCHASE_AMOUNT_HIGHER_THAN_TOTAL_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
