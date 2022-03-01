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
    private final int maxQuantity;

    public Money(int value) {
        validateValueRange(value);
        this.value = value;
        this.maxQuantity = value / 1000;
    }

    private void validateValueRange(int number) {
        if (MIN_VALUE > number || number > MAX_VALUE) {
            throw new IllegalArgumentException(VALUE_RANGE_ERROR_MESSAGE);
        }
    }

    public void validateQuantityOfManual(int quantityOfManual) {
        if (quantityOfManual < 0 || quantityOfManual > maxQuantity) {
            throw new IllegalArgumentException(QUANTITY_OF_MANUAL_PREFIX + maxQuantity + QUANTITY_OF_MANUAL_SUFFIX);
        }
    }

    public int getQuantityOfAuto(int quantityOfManual) {
        return maxQuantity - quantityOfManual;
    }

    public double calculateRateOfProfit(long totalProfit) {
        return Math.round((double) totalProfit / value * DECIMAL_PLACE) / DECIMAL_PLACE;
    }
}
