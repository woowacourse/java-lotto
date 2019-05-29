package lotto.view.inputview;

public abstract class AbstractParser {
    private static final String ERROR_NULL_OR_NO_INPUT = "입력이 없습니다.";

    public static void checkNullEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new NullPointerException(ERROR_NULL_OR_NO_INPUT);
        }
    }
}
