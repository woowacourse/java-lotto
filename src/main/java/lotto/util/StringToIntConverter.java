package lotto.util;

public class StringToIntConverter {

    public static int toInt(String input) {
        validateNullOrBlank(input);
        validateNumberFormat(input);
        return Integer.parseInt(input);
    }

    private static void validateNullOrBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("구입 금액은 공백일 수 없습니다");
        }
    }

    private static void validateNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자여야합니다");
        }
    }
}
