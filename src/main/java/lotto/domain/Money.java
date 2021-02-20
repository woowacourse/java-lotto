package lotto.domain;

import lotto.exception.IllegalDivisorCountException;
import lotto.exception.IllegalMoneyException;

import java.util.regex.Pattern;

public class Money {
    public static final Money ZERO = new Money(0);
    public static final int LOTTO_PRICE = 1000;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    private final long value;

    public Money(String input) {
        validateMoneyValue(input);
        this.value = Long.parseLong(input);
    }

    public Money(long input) {
        this.value = input;
    }

    private void validateMoneyValue(String input) {
        if (isInvalidNumberFormat(input) || isLessThanMinimumMoney(input)) {
            throw new IllegalMoneyException();
        }
    }

    private boolean isInvalidNumberFormat(String input) {
        return !NUMBER_PATTERN.matcher(input).matches();
    }

    private boolean isLessThanMinimumMoney(String input) {
        return Integer.parseInt(input) < LOTTO_PRICE;
    }

    public Money plus(Money money) {
        if (money.value == 0) {
            return this;
        }
        return new Money(money.value + this.value);
    }

    public Money multiple(int count) {
        return new Money(this.value * count);
    }

    public double divide(int count) {
        if (count <= 0) {
            throw new IllegalDivisorCountException();
        }
        return this.value / (LOTTO_PRICE * count);
    }

    public long getValue() {
        return value;
    }
}
