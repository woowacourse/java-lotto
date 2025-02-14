package lotto.domain;

import static lotto.util.Constant.LOTTO_MONEY_UNIT;
import static lotto.util.ErrorHandler.INVALID_AMOUNT;
import static lotto.util.ErrorHandler.INVALID_NUMBER;
import static lotto.util.ErrorHandler.INVALID_UNIT;

import lotto.util.StringConverter;

public class LottoMoney {

    private final int lottoMoney;

    public LottoMoney(String input) {
        int amount = StringConverter.parseToInt(input, INVALID_NUMBER);
        validate(amount);
        this.lottoMoney = amount;
    }

    private void validate(int amount) {
        validateAmount(amount);
        validateUnit(amount);
    }

    private void validateAmount(int amount) {
        if (amount < LOTTO_MONEY_UNIT) {
            throw INVALID_AMOUNT.getException();
        }
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_MONEY_UNIT != 0) {
            throw INVALID_UNIT.getException();
        }
    }

    public int getLottoMoney() {
        return lottoMoney;
    }
}
