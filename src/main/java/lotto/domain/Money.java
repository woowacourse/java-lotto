package lotto.domain;

public class Money {

    private static final int MAX_PRICE = 2_000_000_000;
    private static final int REMAINDER = 0;

    private static final String TO_INT_ERROR_MESSAGE = "숫자여야 합니다.";
    private static final String VALUE_RANGE_ERROR_MESSAGE = String.format("%d부터 20억의 숫자여야 합니다.", Lotto.PRICE);
    private static final String VALUE_DIVIDED_BY_ERROR_MESSAGE = String.format("%d으로 나누어 떨어져야 합니다.", Lotto.PRICE);

    private final int value;

    public Money(String text) {
        int number = toInt(text);
        validate(number);
        this.value = number;
    }

    private int toInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(TO_INT_ERROR_MESSAGE);
        }
    }

    private void validate(int number) {
        validateValueRange(number);
        validateDividedByLottoPrice(number);
    }

    private void validateValueRange(int number) {
        if (Lotto.PRICE > number || number > MAX_PRICE) {
            throw new IllegalArgumentException(VALUE_RANGE_ERROR_MESSAGE);
        }
    }

    private void validateDividedByLottoPrice(int number) {
        if (number % Lotto.PRICE != REMAINDER) {
            throw new IllegalArgumentException(VALUE_DIVIDED_BY_ERROR_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
