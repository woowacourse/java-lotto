package lotto.domain;

import lotto.exceptions.InvalidManualQuantityException;
import lotto.exceptions.InvalidMoneyException;

public class Money {
    static final int TICKET_PRICE = 1000;
    private final int amount;

    Money(final String amount) {
        this.amount = getValidatedMoney(amount);
    }

    public static Money create(String amount) {
        return new Money(amount);
    }

    private int getValidatedMoney(String amount) {
        int parsedAmount = parseAmountToInteger(amount);
        validateAmount(parsedAmount);
        return parsedAmount;
    }

    private void validateAmount(int parsedAmount) {
        if (isInvalid(parsedAmount)) {
            throw new InvalidMoneyException("로또 구입금액은 1000원부터 가능합니다.");
        }
    }

    private int parseAmountToInteger(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new InvalidMoneyException("올바른 형식의 입력이 아닙니다.");
        }
    }

    private boolean isInvalid(int amount) {
        return amount < TICKET_PRICE;
    }

    public int calculateTicketQuantity() {
        return this.amount / TICKET_PRICE;
    }

    public int change() {
        return amount - calculateTicketQuantity() * TICKET_PRICE;
    }

    public int getValidatedManualQuantity(String manualQuantity) {
        int parsedQuantity = parseQuantityToInteger(manualQuantity);
        validateQuantity(parsedQuantity);
        return parsedQuantity;
    }

    private void validateQuantity(int parsedQuantity) {
        if (parsedQuantity < 0) {
            throw new InvalidManualQuantityException("구입 가능한 수동 로또의 최소 개수는 0개 이상입니다.");
        }
        int ticketQuantity = this.calculateTicketQuantity();
        if (parsedQuantity > ticketQuantity) {
            throw new InvalidManualQuantityException("지불하신 금액으로 살 수 있는 총 로또의 수는 " + ticketQuantity + "개입니다.");
        }
    }

    private int parseQuantityToInteger(String manualQuantity) {
        try {
            return Integer.parseInt(manualQuantity);
        } catch (NumberFormatException e) {
            throw new InvalidManualQuantityException("올바른 형식의 입력이 아닙니다.");
        }
    }
}
