package lotto.utils;

public class IntegerUtils {

    public static int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력한 값이 숫자가 아닙니다.");
        }
    }
}
