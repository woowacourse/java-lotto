package common;

public final class NumberParser {

    private NumberParser() {
    }

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 해당 입력값은 숫자여야 합니다.");
        }
    }
}
