package lotto.domain;

import static lotto.common.exception.ErrorMessage.*;

public record Money(int amount, int lottoPrice) {

    public Money {
        validate(amount, lottoPrice);
    }

    private void validate(int amount, int lottoPrice) {
        validateEnoughMoney(amount, lottoPrice);
        validateDivided(amount, lottoPrice);
    }

    private void validateEnoughMoney(int amount, int lottoPrice) {
        if (amount < lottoPrice) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_ENOUGH);
        }
    }

    private void validateDivided(int money, int lottoPrice) {
        if (money % lottoPrice != 0) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_DIVIDED);
        }
    }

}
