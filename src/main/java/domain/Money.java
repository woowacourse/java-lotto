package domain;

import java.util.Objects;

/**
 * Money.java
 * 로또 프로그램 내에서 통용되는 돈을 뜻하는 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon
 * @author Daeun Lee, github.com/da-nyee
 */
public class Money {
    private static final String ERROR_INVALID_INPUT_FORMAT = "[ERROR] 금액은 숫자만 입력해주세요.";
    private static final String ERROR_NEGATIVE_NUMBER = "[ERROR] 양수만 입력해주세요.";
    private static final String ERROR_INVALID_SUBTRACTION = "[ERROR] 뺼셈이 불가능합니다. (금액은 0원 이하로 떨어질 수 없습니다)";
    private static final String LONG_REGULAR_EXPRESSION = "^-?[0-9]+$";
    private static final long ZERO = 0;

    private final long money;

    public Money(String input) {
        validateInputFormat(input);
        long money = Long.parseLong(input);
        validatePositiveNumber(money);
        this.money = money;
    }

    public Money(long money) {
        validatePositiveNumber(money);
        this.money = money;
    }

    private void validateInputFormat(String input) {
        if (input.matches(LONG_REGULAR_EXPRESSION)) {
            return;
        }
        throw new IllegalArgumentException(ERROR_INVALID_INPUT_FORMAT);
    }

    private void validatePositiveNumber(long input) {
        if (input < ZERO) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_NUMBER);
        }
    }

    public long toLong() {
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

    public Money subtract(Money money) {
        long value = this.money - money.toLong();
        if (value < ZERO) {
            throw new IllegalArgumentException(ERROR_INVALID_SUBTRACTION);
        }
        return new Money(value);
    }

    public Money multiply(int multiplicand) {
        return new Money(this.money * multiplicand);
    }
}
