package utils;

public class IntegerParser {
    private static final String NO_INTEGER_VALUE_ERROR_MESSAGE = "[ERROR] 입력한 값이 숫자가 아닙니다.";

    private static int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(NO_INTEGER_VALUE_ERROR_MESSAGE);
        }
    }
}
