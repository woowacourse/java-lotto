public class Number {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    private static final String ERROR_MESSAGE = MIN_VALUE + "부터 " + MAX_VALUE + "의 숫자여야 합니다.";

    private final int value;

    public Number(final int number) {
        validateValueRange(number);
        this.value = number;
    }

    private void validateValueRange(final int number) {
        if (MIN_VALUE > number || number > MAX_VALUE) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
