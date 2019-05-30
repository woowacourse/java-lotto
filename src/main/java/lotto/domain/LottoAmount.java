package lotto.domain;

import lotto.utils.NullCheckUtil;

import java.util.Objects;

public class LottoAmount {
    private static final int MINIMUM_MONEY = 0;
    private static final int LIMIT_MINIMUM_PRICE = 1000;
    private static final int PRICE_UNIT = 1000;
    private static final int DIVISIBLE = 0;
    private static final String ERROR_NEGATIVE_VALUE = "금액은 음수 값이 아닙니다.";
    private static final String ERROR_LIMIT_MINIMUM_PRICE = "구매 금액은 1000원 이상입니다.";
    private static final String ERROR_PRICE_UNIT = "구매 금액은 1,000원 단위 입니다.";

    private final Integer lottoAmount;

    private LottoAmount(Integer purchasePrice) {
        checkValidPurchasePrice(purchasePrice);
        this.lottoAmount = calculateLottoAmount(purchasePrice);
    }

    public static LottoAmount createLottoAmount(Integer purchasePrice) {
        return new LottoAmount(purchasePrice);
    }

    private void checkValidPurchasePrice(Integer purchasePrice) {
        NullCheckUtil.checkNull(purchasePrice);
        checkNegative(purchasePrice);
        checkMinimumLimit(purchasePrice);
        checkDivisiblePriceUnit(purchasePrice);
    }

    private void checkNegative(Integer purchasePrice) {
        if (purchasePrice < MINIMUM_MONEY) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_VALUE);
        }
    }

    private void checkMinimumLimit(Integer purchasePrice) {
        if (purchasePrice < LIMIT_MINIMUM_PRICE) {
            throw new IllegalArgumentException(ERROR_LIMIT_MINIMUM_PRICE);
        }
    }

    private void checkDivisiblePriceUnit(Integer purchasePrice) {
        if (isIndivisiblePriceUnit(purchasePrice)) {
            throw new IllegalArgumentException(ERROR_PRICE_UNIT);
        }
    }

    private boolean isIndivisiblePriceUnit(Integer purchasePrice) {
        return purchasePrice % PRICE_UNIT != DIVISIBLE;
    }

    private int calculateLottoAmount(Integer purchasePrice) {
        return purchasePrice / PRICE_UNIT;
    }

    public boolean isEqualsAmount(Integer number) {
        return this.lottoAmount.equals(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoAmount that = (LottoAmount) o;
        return Objects.equals(lottoAmount, that.lottoAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoAmount);
    }
}
