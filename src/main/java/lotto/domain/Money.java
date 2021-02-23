package lotto.domain;

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
        checkInvalidNumberFormat(input);
        checkLessThanMinimumMoney(input);
    }

    private void checkInvalidNumberFormat(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 구입 금액이 정수가 아닙니다.");
        }
    }

    private void checkLessThanMinimumMoney(String input) {
        if (Integer.parseInt(input) < LOTTO_PRICE) {
            throw new IllegalArgumentException("[ERROR] 구입 금액이 로또 금액보다 작습니다.");
        }
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
            throw new ArithmeticException("[ERROR] 0으로 나눌 수 없습니다.");
        }
        return this.value / (LOTTO_PRICE * count);
    }

    public long getValue() {
        return value;
    }

    public int getPurchaseCount() {
        return (int) value / LOTTO_PRICE;
    }
}
