package lotto.domain.primitive;

public class Money {

    private static final String NON_POSITIVE_ERROR_MESSAGE = "돈은 양의 정수여야 합니다.";
    private int value;

    public Money(final int value) {
        validatePositiveInt(value);
        this.value = value;
    }

    private void validatePositiveInt(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(NON_POSITIVE_ERROR_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
