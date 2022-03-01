package lotterymachine.vo;

import static lotterymachine.model.ErrorMessage.NOT_PURCHASABLE;

import java.util.Objects;

public class Money {
    private static final int TICKET_PRICE = 1000;

    private final int amount;

    private Money(int amount) {
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

    public int divideByTicketPrice() {
        return this.getAmount() / TICKET_PRICE;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
