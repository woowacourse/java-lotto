package lotto.domain;

import static lotto.util.Constant.LOTTO_MONEY_UNIT;
import static lotto.util.ErrorHandler.INVALID_LOTTO_MONEY_AMOUNT;
import static lotto.util.ErrorHandler.INVALID_LOTTO_MONEY_UNIT;

public record LottoMoney(int amount) {

    public LottoMoney {
        validate(amount);
    }

    private void validate(int amount) {
        validateAmount(amount);
        validateUnit(amount);
    }

    private void validateAmount(int amount) {
        if (amount < LOTTO_MONEY_UNIT) {
            throw INVALID_LOTTO_MONEY_AMOUNT.getException();
        }
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_MONEY_UNIT != 0) {
            throw INVALID_LOTTO_MONEY_UNIT.getException();
        }
    }
}
