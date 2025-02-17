package domain;

import exception.AppException;

public class PurchaseAmount {

    public static final String INVALID_PURCHASEAMOUNT_UNIT = "구입 금액은 1000 단위 숫자로 입력해야 합니다.";
    public static final String INVALID_PURCHASEAMOUNT_RANGE = "구입 금액은 1000원 이상이어야 합니다.";
    public static final String INVALID_PURCHASEAMOUNT_FORMAT = "구입 금액은 숫자여야 합니다.";

    private static final int UNIT = 1_000;

    private final int amount;

    public PurchaseAmount(final int amount) {
        validateRange(amount);
        validateAmount(amount);
        this.amount = amount;
    }

    public int getMatchCount() {
        return amount / UNIT;
    }

    private void validateAmount(final int amount) {
        if (amount % UNIT != 0) {
            throw new AppException(INVALID_PURCHASEAMOUNT_UNIT);
        }
    }

    private void validateRange(final int amount) {
        if (amount < UNIT) {
            throw new AppException(INVALID_PURCHASEAMOUNT_RANGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
