package domain;

import static domain.LottoMachine.LOTTO_PRICE;

public class Money {

    private static final String ERROR_LESS_THAN_MINIMUM_MONEY = "가격은 1000원 이상만 가능합니다.";
    private static final String ERROR_NOT_DIVIDED_LOTTO_PRICE = "투입 금액은 로또 가격(1000원)의 배수여야 합니다.";
    private static final String ERROR_INVALID_MONEY = "보유한 돈은 0보다 적을 수 없습니다.";

    private int money;

    public Money(final int money) {
        checkUnderMinimumMoney(money);
        checkDividedByLottoPrice(money);
        this.money = money;
    }

    private static void checkUnderMinimumMoney(final int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_LESS_THAN_MINIMUM_MONEY);
        }
    }

    private static void checkDividedByLottoPrice(final int money) {
        if (money% LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_LOTTO_PRICE);
        }
    }

    public int numOfAvailablePurchase() {
        return money / LOTTO_PRICE;
    }

    public void purchaseLotto(int numOfLotto) {
        money -= (LOTTO_PRICE * numOfLotto);
        checkInvalidMoney();
    }

    private void checkInvalidMoney() {
        if (money < 0) {
            throw new IllegalStateException(ERROR_INVALID_MONEY);
        }
    }
}
