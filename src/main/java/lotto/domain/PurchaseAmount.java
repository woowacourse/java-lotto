package lotto.domain;

import static lotto.constant.ErrorMessage.ERROR_PURCHASE_AMOUNT_WRONG_UNIT;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;
    private static final int REMAIN_ZERO = 0;

    private final int amount;

    public PurchaseAmount(final int amount) {
        validateUnit(amount);
        this.amount = amount;
    }

    public boolean canPurchase(final int count) {
        return amount >= LOTTO_PRICE * count;
    }

    public int calculateAutoLottoCount(final int count) {
        return (amount - LOTTO_PRICE * count) / LOTTO_PRICE;
    }

    private void validateUnit(final int amount) {
        if (amount % LOTTO_PRICE != REMAIN_ZERO) {
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_WRONG_UNIT.message());
        }
    }

    public int getAmount() {
        return amount;
    }
}
