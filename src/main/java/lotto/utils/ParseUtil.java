package lotto.utils;

public class ParseUtil {
    private ParseUtil() {
    }

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 입력이 숫자가 아닙니다.");
        }
    }
}
