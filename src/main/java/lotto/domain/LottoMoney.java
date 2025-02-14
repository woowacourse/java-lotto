package lotto.domain;

import static lotto.util.Constant.LOTTO_MONEY_UNIT;
import static lotto.util.ErrorHandler.INVALID_LOTTO_MONEY_AMOUNT;
import static lotto.util.ErrorHandler.INVALID_LOTTO_MONEY_NUMBER;
import static lotto.util.ErrorHandler.INVALID_LOTTO_MONEY_UNIT;

public class LottoMoney {

    private final int lottoMoney;

    public LottoMoney(String input) {
        int amount = validateAndParse(input);
        validate(amount);
        this.lottoMoney = amount;
    }

    private int validateAndParse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw INVALID_LOTTO_MONEY_NUMBER.getException();
        }
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

    public int getLottoMoney() {
        return lottoMoney;
    }
}
