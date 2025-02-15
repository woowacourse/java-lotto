package lotto.domain;

import lotto.common.ErrorMessage;

public class Money {
    static final int LOTTO_PRICE = 1000;
    private final int amount;

    public Money(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public void validateAmount(int amount) {
        if (amount % LOTTO_PRICE != 0 || amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_INPUT.getMessage());
        }
    }

    public int getLottoTicketCount() {
        return amount / LOTTO_PRICE;
    }

    public int getAmount() {
        return amount;
    }
}


