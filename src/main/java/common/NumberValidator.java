package common;

public final class NumberValidator {

    private NumberValidator() {
    }

    public static void validatePositive(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("[ERROR] 해당 입력값은 양수여합니다.");
        }
    }
}
