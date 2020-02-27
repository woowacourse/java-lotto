package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import lotto.utils.NumberUtils;

public class ManualPurchaseCount {
    private final int purchasedCount;

    public ManualPurchaseCount(final String userInputCount, final Payment payment) {
        int purchasedCount = NumberUtils.parseNumber(userInputCount);
        validateLessThanLottoCount(purchasedCount, payment);
        validatePositiveNumber(purchasedCount);
        this.purchasedCount = purchasedCount;
    }

    public static void validateLessThanLottoCount(final int manualCount, final Payment payment) {
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
