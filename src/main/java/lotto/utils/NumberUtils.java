package lotto.utils;

public final class NumberUtils {

    private NumberUtils() {
    }

    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 가능합니다.");
        }
    }
}
