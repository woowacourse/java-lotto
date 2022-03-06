package lotto.domain;

public final class AmountToBuyLotto {
    private static final int LOWER_BOUND = 1000;
    private static final double DECIMAL = 1.0;
    private static final String AMOUNT_PREFIX = "금액은";
    private static final String NOT_LOWER_BOUND_ERROR = String.format("%s %s 이상이어야 합니다.", AMOUNT_PREFIX, LOWER_BOUND);
    private static final String NOT_DIVISIBLE_NUMBER_ERROR = String.format("%s %s 단위여야 합니다.", AMOUNT_PREFIX,
            LOWER_BOUND);

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
        return manualLottoCount.subtractBy(calculateLottoCount());
    }

    @Override
    public String toString() {
        return "Amount{" +
                "amount=" + value +
                '}';
    }
}
