package global.utils;

public final class Validator {

    private static final String NUMERIC_INPUT_ONLY_MESSAGE = "숫자만 입력할 수 있습니다.";
    private static final String RANGE_INPUT_ONLY_MESSAGE = "범위 내의 값만 입력할 수 있습니다.";

    private Validator() {
    }

    public static void validateNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMERIC_INPUT_ONLY_MESSAGE);
        }
    }

    public static void validateNumberRange(final int number, final int min, final int max) {
        if (number > max || number < min) {
            throw new IllegalArgumentException(RANGE_INPUT_ONLY_MESSAGE);
        }
    }
}
