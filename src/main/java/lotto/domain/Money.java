package lotto.domain;

import lotto.exceptions.InvalidMoneyException;

public class Money {
    private int amount;

    public Money(String amount) {
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
            throw new InvalidMoneyException("로또 구입금액은 1000원 단위로 가능합니다.");
        }
        return parsedAmount;
    }

    private boolean isInvalid(int amount) {
        return amount % 1000 != 0 || amount < 1000;
    }

    public int ticketQuantity() {
        return this.amount/1000;
    }
}
