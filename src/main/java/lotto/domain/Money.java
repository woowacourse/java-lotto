package lotto.domain;

import static lotto.common.constant.Constant.LOTTO_PRIZE;
import static lotto.common.exception.ErrorMessage.*;

public record Money(int money) {

    public Money {
        validate(money);
    }


    private void validate(int money) {
        validateEnoughMoney(money);
        validateDivided(money);
    }
    private void validateEnoughMoney(int money) {
        if (isNotEnough(money)) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_ENOUGH);
        }
    }

    private void validateDivided(int money) {
        if (isDivided(money)) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_DIVIDED);
        }
    }

    private boolean isNotEnough(int money) {
        return money < LOTTO_PRIZE;
    }

    private boolean isDivided(int money) {
        return money % LOTTO_PRIZE != 0;
    }

    public int getAmount() {
        return money / LOTTO_PRIZE;
    }

}
