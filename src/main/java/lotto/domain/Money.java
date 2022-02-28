package lotto.domain;

import lotto.domain.lotto.Lotto;

public class Money {

    private static final int MAX_PRICE = 2_000_000_000;
    private static final int REMAINDER = 0;
    private static final double ROUND_OFF_NUMBER = 1e3;

    private final int value;

    public Money(String text) {
        int number = toInt(text);
        validate(number);
        this.value = number;
    }

    public double getRateOfProfit(long totalMoney) {
        return Math.round((double)totalMoney / value * ROUND_OFF_NUMBER) / ROUND_OFF_NUMBER;
    }

    public int getValue() {
        return value;
    }

    private int toInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자여야 합니다.");
        }
    }

    private void validate(int number) {
        validateValueRange(number);
        validateDividedByLottoPrice(number);
    }

    private void validateValueRange(int number) {
        if (Lotto.PRICE > number || number > MAX_PRICE) {
            throw new IllegalArgumentException(String.format("%d부터 20억의 숫자여야 합니다.", Lotto.PRICE));
        }
    }

    private void validateDividedByLottoPrice(int number) {
        if (number % Lotto.PRICE != REMAINDER) {
            throw new IllegalArgumentException(String.format("%d으로 나누어 떨어져야 합니다.", Lotto.PRICE));
        }
    }
}
