package lotto.domain;

import lotto.exceptions.InvalidMoneyException;

public class Money {
    public static final int TICKET_PRICE = 1000;
    private final int amount;

    public Money(final String amount) {
        this.amount = validNumber(amount);
    }

    private int validNumber(String amount) {
        int parsedAmount;
        try {
            parsedAmount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new InvalidMoneyException("올바른 형식의 입력이 아닙니다.");
        }
        if (isInvalid(parsedAmount)) {
            throw new InvalidMoneyException("로또 구입금액은 1000원부터 가능합니다.");
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
