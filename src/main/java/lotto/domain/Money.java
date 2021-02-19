package lotto.domain;

import lotto.exception.IllegalMoneyException;

import java.util.regex.Pattern;

public class Money {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    public static final int MINIMUM_MONEY = 1000;
    public static final Money ZERO = new Money(0);

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
        return Integer.parseInt(input) < MINIMUM_MONEY;
    }
    public Money plus(Money money){
        return new Money(money.getValue() + this.value);
    }
    public Money multiple(int number){
        return new Money(this.value * number);
    }

    public double getRate(Money money){
        return this.value / money.getValue();
    }

    public long getValue() {
        return value;
    }
}
