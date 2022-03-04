package domain;

import static domain.LottoMachine.LOTTO_PRICE;

public class Money {

    private static final String ERROR_LESS_THAN_MINIMUM_MONEY = "가격은 1000원 이상만 가능합니다.";

    private int money;

    public Money(final int money) {
        checkUnderMinimumMoney(money);
        this.money = money;
    }

    private static void checkUnderMinimumMoney(final int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_LESS_THAN_MINIMUM_MONEY);
        }
    }

    public int numOfAvailablePurchase() {
        return money / LOTTO_PRICE;
    }

    public void purchaseLotto(int numOfLotto) {
        money -= (LOTTO_PRICE * numOfLotto);
    }
}
