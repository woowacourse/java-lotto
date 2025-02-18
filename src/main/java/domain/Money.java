package domain;

import static util.constant.Message.PRICE_NEGATIVE_ERROR;
import static util.constant.Values.LOTTO_UNIT;
import static util.constant.Values.MONEY_MIN_NUM;

public class Money {

    private final int money;

    public Money(int money) {
        validateRange(money);
        this.money = money;
    }

    public int calculateTotalLotto() {
        return money / LOTTO_UNIT;
    }

    public double calculateProfitRate(int profit) {
        return (double) profit / money;
    }

    private void validateRange(int money) {
        if (money < MONEY_MIN_NUM) {
            throw new IllegalArgumentException(PRICE_NEGATIVE_ERROR);
        }
    }
}
