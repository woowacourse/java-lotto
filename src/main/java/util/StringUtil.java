package util;

public class StringUtil {

    private static final String NONE_NUMERIC_ERROR = "[ERROR] 숫자로 변환하려는 String에 숫자가 아닌 문자가 있습니다.";

    private StringUtil() {}

    public static int convertToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NONE_NUMERIC_ERROR);
        }
    }
}
