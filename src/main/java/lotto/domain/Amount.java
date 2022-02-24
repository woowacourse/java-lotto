package lotto.domain;

public class Amount {
    private static final int DIVISOR = 1000;
    private static final double DECIMAL = 1.0;
    private static final String NUMBER_REGAX = "^[0-9]*$";
    private static final String EMPTY_ERROR = "빈 값은 불가능합니다.";
    private static final String NOT_NUMBER_ERROR = "금액은 숫자만 등록 가능합니다.";
    private static final String NOT_NATURAL_NUMBER_ERROR = "금액은 0 이상이어야 합니다.";
    private static final String NOT_DIVISIBLE_NUMBER_ERROR = "금액은 " + DIVISOR + "단위여야 합니다.";

    private int amount;

    public Amount(String amount) {
        validateAmount(amount);
        this.amount = Integer.parseInt(amount);
    }

    private void validateAmount(String amount) {
        validateEmpty(amount);
        validateNumber(amount);
        int amountNum = Integer.parseInt(amount);
        validateNatural(amountNum);
        validateDivisible(amountNum);
    }

    private void validateEmpty(String amount) {
        if (amount == null || amount.isBlank()) {
            throw new IllegalArgumentException(EMPTY_ERROR);
        }
    }

    private void validateNumber(String amount) {
        if (!amount.matches(NUMBER_REGAX)) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR);
        }
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

    public int calculateLottoCount() {
        return amount / DIVISOR;
    }

    public double calculateProfit(long prizeSum) {
        return DECIMAL * prizeSum / amount;
    }
}
