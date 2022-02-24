package lotto.domain;

public class Amount {
    private static final int DIVISOR = 1000;
    private static final double DECIMAL = 1.0;
    private static final String NOT_NUMBER_ERROR = "금액은 숫자만 등록 가능합니다.";
    private static final String NOT_NATURAL_NUMBER_ERROR = "금액은 " + DIVISOR + " 이상이어야 합니다.";
    private static final String NOT_DIVISIBLE_NUMBER_ERROR = "금액은 " + DIVISOR + "단위여야 합니다.";

    private final int amount;

    public Amount(String input) {
        int amount = convertToInt(input);
        validateAmount(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / DIVISOR;
    }

    public double calculateProfit(long prizeSum) {
        return DECIMAL * prizeSum / amount;
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR);
        }
    }

    private void validateAmount(int amount) {
        validateNatural(amount);
        validateDivisible(amount);
    }

    private void validateNatural(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(NOT_NATURAL_NUMBER_ERROR);
        }
    }

    private void validateDivisible(int amount) {
        if (amount % DIVISOR != 0) {
            throw new IllegalArgumentException(NOT_DIVISIBLE_NUMBER_ERROR);
        }
    }
}
