package lotto.view;

public class InputValidator {

    public static void validateNullOrBlank(final String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력값은 null일 수 없습니다.");
        }
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력값은 빈 값일 수 없습니다.");
        }
    }
}
