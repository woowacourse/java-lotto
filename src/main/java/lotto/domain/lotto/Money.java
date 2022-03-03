package lotto.domain.lotto;

import java.util.Objects;

public class Money {

    private static final int MAX_PRICE = 2_000_000_000;
    private static final int REMAINDER = 0;
    private static final double ROUND_OFF_NUMBER = 1e3;

    private int value;

    public Money(int value) {
        validate(value);
        this.value = value;
    }

    public Count countToBuyLotto() {
        return new Count(value / Lotto.PRICE);
    }

    public void validateBuyableLottoCount(Count count) {
        int buyableCount = value / Lotto.PRICE;
        if (value < count.value() * Lotto.PRICE) {
            throw new IllegalArgumentException(String.format("%d장 까지 살 수 있습니다.", buyableCount));
        }
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

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Money money = (Money)object;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
