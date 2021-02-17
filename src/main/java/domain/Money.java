package domain;

import java.util.Objects;

public class Money {
    private static final String INTEGER_REGULAR_EXPRESSION = "^-?[0-9]+$";
    private static final int ZERO = 0;

    private final int money;

    public Money(String input) {
        validateInputFormat(input);
        int money = Integer.parseInt(input);
        validatePositiveNumber(money);
        this.money = money;
    }

    private void validateInputFormat(String input) {
        if (input.matches(INTEGER_REGULAR_EXPRESSION)) {
            return;
        }
        throw new InvalidInputException();
    }

    private void validatePositiveNumber(int input) {
        if (input < ZERO) {
            throw new InvalidNumberRangeException("양수만 입력해주세요.");
        }
    }

    public int toInteger() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
