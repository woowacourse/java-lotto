package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    public Money(int amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money parse(String text) {
        if (isNotNumeric(text)) {
            throw new IllegalArgumentException("입금액은 반드시 숫자여야 합니다.");
        }
        return new Money(Integer.parseInt(text));
    }

    private static boolean isNotNumeric(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Money add(Money prize) {
        return new Money(this.amount.add(prize.amount));
    }

    public Money multiply(int factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)));
    }

    public BigDecimal divide(Money money) {
        return this.amount.divide(money.amount);
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
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
