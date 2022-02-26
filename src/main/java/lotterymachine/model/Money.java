package lotterymachine.model;

import static lotterymachine.model.ErrorMessage.NOT_PURCHASABLE;

public class Money {
    private static final int TICKET_PRICE = 1000;

    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    public static Money fromInputAmount(int amount) {
        validate(amount);
        return new Money(amount);
    }

    private static void validate(int amount) {
        if (amount < TICKET_PRICE) {
            throw new IllegalArgumentException(NOT_PURCHASABLE.getMessage());
        }
    }

    public double divide(Money money) {
        return (double) this.amount / money.amount;
    }

    public int getAmount() {
        return amount;
    }

    public int divideByTicketPrice() {
        return this.getAmount() / TICKET_PRICE;
    }
}
