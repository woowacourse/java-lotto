package lotto.domain.primitive;

public class Money {

    private final int value;

    public Money(final int value) {
        validatePositiveInt(value);
        this.value = value;
    }

    private void validatePositiveInt(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("돈은 양의 정수여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
