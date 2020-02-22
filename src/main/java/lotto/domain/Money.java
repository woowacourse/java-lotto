package lotto.domain;

import lotto.exceptions.InvalidMoneyException;

public class Money {
    static final int TICKET_PRICE = 1000;

    private final int amount;

    private Money(final String amount) {
        this.amount = validMoney(amount);
    }

    public static Money create(String amount) {
        return new Money(amount);
    }

    private int validMoney(String amount) {
        int parsedAmount = validNumber(amount);
        validateAmount(parsedAmount);
        return parsedAmount;
    }

    private void validateAmount(int parsedAmount) {
        if (isInvalid(parsedAmount)) {
            throw new InvalidMoneyException("로또 구입금액은 1000원부터 가능합니다.");
        }
    }

    private int validNumber(String amount) {
        int parsedAmount;
        try {
            parsedAmount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new InvalidMoneyException("올바른 형식의 입력이 아닙니다.");
        }
        return parsedAmount;
    }

    private boolean isInvalid(int amount) {
        return amount < TICKET_PRICE;
    }

    public int ticketQuantity() {
        return this.amount / TICKET_PRICE;
    }

    public int change() {
        return amount - ticketQuantity() * TICKET_PRICE;
    }
}
