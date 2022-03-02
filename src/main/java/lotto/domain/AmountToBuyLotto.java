package lotto.domain;

import java.util.Objects;

public final class AmountToBuyLotto {
    private static final int LOWER_BOUND = 1000;
    private static final double DECIMAL = 1.0;
    private static final String NOT_LOWER_BOUND_ERROR = "금액은 " + LOWER_BOUND + " 이상이어야 합니다.";
    private static final String NOT_DIVISIBLE_NUMBER_ERROR = "금액은 " + LOWER_BOUND + "단위여야 합니다.";

    private final int value;

    public AmountToBuyLotto(int amount) {
        validateAmount(amount);
        this.value = amount;
    }

    private void validateAmount(int amount) {
        validateLowerBound(amount);
        validateDivisible(amount);
    }

    private void validateLowerBound(int amount) {
        if (amount < LOWER_BOUND) {
            throw new IllegalArgumentException(NOT_LOWER_BOUND_ERROR);
        }
    }

    private void validateDivisible(int amount) {
        if (amount % LOWER_BOUND != 0) {
            throw new IllegalArgumentException(NOT_DIVISIBLE_NUMBER_ERROR);
        }
    }

    public int calculateLottoCount() {
        return value / LOWER_BOUND;
    }

    public double calculateProfit(long prizeSum) {
        return DECIMAL * prizeSum / value;
    }


    public int calculateAutomaticLottoCount(ManualLottoCount manualLottoCount) {
        return calculateLottoCount() - manualLottoCount.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AmountToBuyLotto amount = (AmountToBuyLotto) o;
        return value == amount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "amount=" + value +
                '}';
    }
}
