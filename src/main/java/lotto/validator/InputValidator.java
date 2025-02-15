package lotto.validator;

import java.util.List;

public class InputValidator {

    public static void validateBlank(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("유효하지 않은 입력값입니다.");
        }
    }

    public static void validateNumberFormat(String numericContent) {
        try {
            Integer.parseInt(numericContent);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자 형식이 아닙니다.");
        }
    }

    public static void validateWinningNumbers(List<String> content) {
        try {
            List<Integer> numbers = content.stream().map(Integer::parseInt).toList();
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("유효하지 않은 입력값입니다.");
        }
    }
}
