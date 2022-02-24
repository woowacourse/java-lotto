package lotto.utils;

public class IntegerUtils {

    // 기본 생성자 생성 방지 (인스턴스화 방지)
    private IntegerUtils() {
    }

    public static int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }
}
