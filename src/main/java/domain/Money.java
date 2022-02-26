package domain;

import static utils.Messages.MONEY_DIVIDE_ERROR_MESSAGE;
import static utils.Messages.MONEY_OVER_THOUSANDS_ERROR_MESSAGE;

public class Money {

    private final int MONEY_UNIT = 1000;

    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        isOverThousand(money);
        isDivideByThousand(money);
    }

    private void isOverThousand(int money) {
        if (money < 1000) {
            throw new IllegalArgumentException(MONEY_OVER_THOUSANDS_ERROR_MESSAGE);
        }
    }

    private void isDivideByThousand(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException(MONEY_DIVIDE_ERROR_MESSAGE);
        }
    }

    public int toLottoCount() {
        return money / MONEY_UNIT;
    }

    public int get() {
        return money;
    }

    public float getProfit(long price) {
        return (float) price / (float) money;
    }
}