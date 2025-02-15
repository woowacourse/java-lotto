package lotto.domain;

import static lotto.common.constant.ErrorMessage.*;

public record Amount(int money) {
    private static final int LOTTO_PRIZE = 1000;

    public Amount {
        validateMinimumAmount(money);
        validateDivisibleByLottoPrice(money);
    }

    private void validateMinimumAmount(int money) {
        if (isLessThanMinimumAmount(money)) {
            throw new IllegalArgumentException(ERROR_MONEY_LESS_THEN_STANDARD.getMessage());
        }
    }

    private void validateDivisibleByLottoPrice(int money) {
        if (isNotDivisibleByLottoPrice(money)) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_BY_STANDARD.getMessage());
        }
    }

    private boolean isLessThanMinimumAmount(int money) {
        return money < LOTTO_PRIZE;
    }

    private boolean isNotDivisibleByLottoPrice(int money) {
        return money % LOTTO_PRIZE != 0;
    }

    public int getAmount() {
        return money / LOTTO_PRIZE;
    }
}
