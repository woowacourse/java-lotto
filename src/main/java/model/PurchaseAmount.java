package model;

import error.ErrorType;

public class PurchaseAmount {

    private final int money;

    public PurchaseAmount(final int money) {
        validateNotPositive(money);
        validateNotDivideLottoPrice(money);
        this.money = money;
    }

    private void validateNotPositive(final int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(ErrorType.PURCHASE_AMOUNT_POSITIVE.getMessage());
        }
    }

    private void validateNotDivideLottoPrice(final int money) {
        if (money % Lotto.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorType.PURCHASE_AMOUNT_NOT_DIVIDE_LOTTO_PRICE.getMessage());
        }
    }

    public int calculateLottoCount() {
        return money / Lotto.LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }
}
