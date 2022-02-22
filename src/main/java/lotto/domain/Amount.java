package lotto.domain;

public class Amount {
    private static final int DIVISOR = 1000;
    private static final String NUMBER_REGAX = "[0-9]";
    private static final String NOT_NUMBER_ERROR = "금액은 숫자만 등록 가능합니다.";
    private static final String NOT_NATURAL_NUMBER_ERROR = "금액은 0 이상이어야 합니다.";
    private static final String NOT_DIVISIBLE_NUMBER_ERROR = "금액은 " + DIVISOR + "단위여야 합니다.";

    private int amount;

    public Amount(String amount) {
        validateAmount(amount);
        this.amount = Integer.parseInt(amount);
    }

    private void validateAmount(String amount) {
        validateNumber(amount);
        int amountNum = Integer.parseInt(amount);
        validateNatural(amountNum);
        validateDivisible(amountNum);
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
}
