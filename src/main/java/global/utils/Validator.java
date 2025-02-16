package global.utils;

public final class Validator {

    private Validator() {
    }

    public static void validateNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    public static void validateRange(final int number, final int min, final int max) {
        if (number > max || number < min) {
            throw new IllegalArgumentException("범위 내의 값만 입력할 수 있습니다.");
        }
    }

    public static void validateDivisibility(final int number, final int division) {
        if (number % division != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위로 나누어 떨어져야 합니다.");
        }
    }
}
