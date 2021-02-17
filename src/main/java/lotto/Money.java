package lotto;

public class Money {

    private int value;

    public Money(final int value) {
        validateNonNegativeInt(value);
        this.value = value;
    }

    private void validateNonNegativeInt(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("돈은 음의 정수면 안됩니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
