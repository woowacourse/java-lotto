package util;

public class NumberFormatStringParser {
    public static int parse(String text) {
        if (isNullOrEmpty(text) || isNotNumeric(text)) {
            throw new IllegalArgumentException("빈 값이나 문자가 올 수 없습니다.");
        }
        return Integer.parseInt(text);
    }

    private static boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }

    private static boolean isNotNumeric(String text) {
        try {
            Integer.parseInt(text);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
