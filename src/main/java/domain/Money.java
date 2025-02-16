package domain;

public class Money {
    private final int value;

    public static Money of(String input) {
        try {
            final int parsed = Integer.parseInt(input.trim());
            return new Money(parsed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.", e);
        }
    }

    public Money(int input) {
        validateIsPositive(input);
        this.value = input;
    }

    public int getValue() {
        return value;
    }

    private void validateIsPositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("구입 금액은 양수만 가능합니다.");
        }
    }
}
