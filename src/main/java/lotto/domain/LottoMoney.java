package lotto.domain;

import static lotto.util.Constant.DEFAULT_VALUE_ZERO;
import static lotto.util.Constant.LOTTO_MONEY_UNIT;
import static lotto.util.ExceptionHandler.INVALID_LOTTO_MONEY_AMOUNT;
import static lotto.util.ExceptionHandler.INVALID_LOTTO_MONEY_UNIT;

public class LottoMoney {

    private final int lottoMoney;
    private final int ticketBuyAmount;

    public LottoMoney(int amount) {
        validate(amount);
        this.lottoMoney = amount;
        this.ticketBuyAmount = amount / LOTTO_MONEY_UNIT;
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
        if (amount % LOTTO_MONEY_UNIT != DEFAULT_VALUE_ZERO) {
            throw INVALID_LOTTO_MONEY_UNIT.getException();
        }
    }

    public int getLottoMoney() {
        return lottoMoney;
    }

    public int getTicketBuyAmount() {
        return ticketBuyAmount;
    }
}
