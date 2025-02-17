package lotto.domain;

import static lotto.constant.ErrorMessage.PURCHASE_AMOUNT_DIVIDE;
import static lotto.constant.ErrorMessage.PURCHASE_AMOUNT_RANGE;
import static lotto.constant.ErrorMessage.PURCHASE_AMOUNT_MAX;
import static lotto.constant.Limit.LOTTO_UNIT_PRICE;
import static lotto.constant.Limit.MAX_PURCHASE_AMOUNT;

public class PurchaseAmount {
    private final int purchaseAmount;

    public PurchaseAmount(final int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validatePurchaseAmount(final int purchaseAmount) {
        if (purchaseAmount % LOTTO_UNIT_PRICE.getValue() != 0) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_DIVIDE.getErrorMessage());
        }

        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_RANGE.getErrorMessage());
        }

        if (purchaseAmount > MAX_PURCHASE_AMOUNT.getValue()) {
            throw  new IllegalArgumentException(PURCHASE_AMOUNT_MAX.getErrorMessage());
        }
    }

    public int calculateLottoAmount() {
        return purchaseAmount / LOTTO_UNIT_PRICE.getValue();
    }
}
