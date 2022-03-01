package model;

import java.math.BigDecimal;
import java.util.Objects;
import util.NumberFormatStringParser;

public class Money {
    private static final int PRICE_AMOUNT_PER_LOTTO = 1000;
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    public Money(int amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money parse(String text) {
        int moneyAmount = NumberFormatStringParser.parse(text);
        if (isMultipleByLottoPrice(moneyAmount)) {
            throw new IllegalArgumentException("입력금은 반드시 1000의 배수여야 합니다.");
        }
        return new Money(moneyAmount);
    }

    private static boolean isMultipleByLottoPrice(int moneyAmount) {
        return moneyAmount % PRICE_AMOUNT_PER_LOTTO != 0;
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
