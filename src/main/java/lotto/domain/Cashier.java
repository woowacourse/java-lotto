package lotto.domain;

import static lotto.common.constant.BusinessRule.*;
import static lotto.common.constant.ErrorMessage.*;

public record Cashier(int money) {

    public Cashier {
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
        return money < LOTTO_PRICE;
    }

    private boolean isNotDivisibleByLottoPrice(int money) {
        return money % LOTTO_PRICE != 0;
    }

    public int getNumberOfLotto() {
        return money / LOTTO_PRICE;
    }
}
