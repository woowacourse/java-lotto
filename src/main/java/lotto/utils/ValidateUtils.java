package lotto.utils;

public class ValidateUtils {
    public static int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("구입 금액은 숫자만 가능합니다.");
        }
    }
}
