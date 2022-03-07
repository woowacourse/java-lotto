package lotterymachine.vo;

import java.util.Objects;

public class Money {
    private static final int TICKET_PRICE = 1000;
    private static final String NOT_PURCHASABLE_EXCEPTION = "1000보다 큰 금액을 입력해야합니다.";

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
            throw new IllegalArgumentException(NOT_PURCHASABLE_EXCEPTION);
        }
    }

    public double divide(Money money) {
        return (double) this.amount / money.amount;
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
