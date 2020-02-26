package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import lotto.utils.NumberUtils;

public class ManualPurchaseCount {
    private int purchasedCount;

    public ManualPurchaseCount(String userInputCount, Payment payment) {
        int purchasedCount = NumberUtils.parseNumber(userInputCount);
        validateLessThanLottoCount(purchasedCount, payment);
        validatePositiveNumber(purchasedCount);
        this.purchasedCount = purchasedCount;
    }

    public static void validateLessThanLottoCount(int manualCount, Payment payment) {
        if (manualCount > payment.getPurchasedCount()) {
            ErrorMessage nowErrorMessage = ErrorMessage.MANUAL_COUNT_OVER_TOTAL;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    public static void validatePositiveNumber(final int manualCount) {
        if (manualCount < 0) {
            ErrorMessage nowErrorMessage = ErrorMessage.NOT_POSITIVE;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    public int getPurchasedCount() {
        return purchasedCount;
    }
}
