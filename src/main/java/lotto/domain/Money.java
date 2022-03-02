package lotto.domain;

import lotto.domain.lotto.Lotto;

public class Money {

    private static final int MAX_PRICE = 2_000_000_000;
    private static final int REMAINDER = 0;
    private static final double ROUND_OFF_NUMBER = 1e3;

    private final int value;

    public Money(int value) {
        validate(value);
        this.value = value;
    }

    public Money pay(int price, int count) {
        if (value < price * count) {
            throw new IllegalArgumentException("돈이 부족합니다.");
        }
        return new Money(value - (count * price));
    }

    public double getRateOfProfit(long totalMoney) {
        return Math.round((double)totalMoney / value * ROUND_OFF_NUMBER) / ROUND_OFF_NUMBER;
    }

    public int getValue() {
        return value;
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
