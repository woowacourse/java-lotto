package lotto.validator;

public class NumberValidator {
    private static final String NUMBER_MATCHES = "-?[0-9]+";
    private static final String ERROR_ONLY_NUMBER = "숫자만 입력할 수 있습니다.";

    private NumberValidator() {
    }

    public static void validateNumber(final String value) {
        if (isBlank(value) || isNotNumber(value)) {
            throw new IllegalArgumentException(ERROR_ONLY_NUMBER);
        }
    }

    private static boolean isBlank(final String value) {
        return value == null || value.isEmpty();
    }

    private static boolean isNotNumber(final String value) {
        return !value.matches(NUMBER_MATCHES);
    }
}
