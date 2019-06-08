package lotto.domain.user;

import lotto.utils.NullCheckUtil;

import java.util.Objects;

public class PurchaseAmount {
    private static final int LIMIT_MINIMUM_PRICE = 1000;
    private static final int PRICE_UNIT = 1000;
    private static final int DIVISIBLE = 0;
    private static final String ERROR_LIMIT_MINIMUM_PRICE = "구매 금액은 1000원 이상입니다.";
    private static final String ERROR_PRICE_UNIT = "구매 금액은 1,000원 단위 입니다.";

    private final int autoLottoAmount;

    private PurchaseAmount(int purchasePrice) {
        checkValidPurchasePrice(purchasePrice);
        this.autoLottoAmount = calculateLottoAmount(purchasePrice);
    }

    private void checkValidPurchasePrice(int purchasePrice) {
        checkMinimumLimit(purchasePrice);
        checkDivisiblePriceUnit(purchasePrice);
    }

    private void checkMinimumLimit(int purchasePrice) {
        if (purchasePrice < LIMIT_MINIMUM_PRICE) {
            throw new IllegalArgumentException(ERROR_LIMIT_MINIMUM_PRICE);
        }
    }

    private void checkDivisiblePriceUnit(int purchasePrice) {
        if (isIndivisiblePriceUnit(purchasePrice)) {
            throw new IllegalArgumentException(ERROR_PRICE_UNIT);
        }
    }

    private boolean isIndivisiblePriceUnit(int purchasePrice) {
        return purchasePrice % PRICE_UNIT != DIVISIBLE;
    }

    private int calculateLottoAmount(int purchasePrice) {
        return purchasePrice / PRICE_UNIT;
    }

    public static PurchaseAmount createLottoAmount(int purchasePrice) {
        NullCheckUtil.checkNullInteger(purchasePrice);
        return new PurchaseAmount(purchasePrice);
    }

    public boolean isEqualsAmount(int number) {
        NullCheckUtil.checkNullInteger(number);
        return this.autoLottoAmount == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PurchaseAmount that = (PurchaseAmount) o;
        return Objects.equals(autoLottoAmount, that.autoLottoAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autoLottoAmount);
    }

    public int getAutoLottoAmount() {
        return autoLottoAmount;
    }
}
