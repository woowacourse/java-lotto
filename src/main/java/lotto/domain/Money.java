package lotto.domain;

public class Money {

    private static final int DIVIDED_STANDARD = 1000;
    private static final int MIN_VALUE = 1000;
    private static final int MAX_VALUE = 2_000_000_000;
    private static final int REMAINDER = 0;
    private static final double DECIMAL_PLACE = 1000.0;
    private static final String VALUE_RANGE_ERROR_MESSAGE = "1000부터 20억의 숫자여야 합니다.";
    private static final String VALUE_DIVIDED_BY_ERROR_MESSAGE = "1000으로 나누어 떨어져야 합니다.";
    private static final String QUANTITY_OF_MANUAL_PREFIX = "0개 이상 ";
    private static final String QUANTITY_OF_MANUAL_SUFFIX = "개 이하의 로또만 수동으로 구매할 수 있습니다.";

    private final int value;

    public Money(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int number) {
        validateValueRange(number);
        validateDividedBy(number);
    }

    private void validateValueRange(int number) {
        if (MIN_VALUE > number || number > MAX_VALUE) {
            throw new IllegalArgumentException(VALUE_RANGE_ERROR_MESSAGE);
        }
    }

    private void validateDividedBy(int number) {
        if (number % DIVIDED_STANDARD != REMAINDER) {
            throw new IllegalArgumentException(VALUE_DIVIDED_BY_ERROR_MESSAGE);
        }
    }

    public int getQuantityOfAuto(int quantityOfManual, int price) {
        final int maxQuantity = getAvailableQuantity(price);
        validateQuantityOfManual(maxQuantity, quantityOfManual);
        return maxQuantity - quantityOfManual;
    }

    private void validateQuantityOfManual(int maxQuantity, int quantityOfManual) {
        if (quantityOfManual < 0 || quantityOfManual > maxQuantity) {
            throw new IllegalArgumentException(QUANTITY_OF_MANUAL_PREFIX + maxQuantity + QUANTITY_OF_MANUAL_SUFFIX);
        }
    }

    public int getAvailableQuantity(int price) {
        return value / price;
    }

    public double calculateRateOfProfit(long totalProfit) {
        return Math.round((double) totalProfit / value * DECIMAL_PLACE) / DECIMAL_PLACE;
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
