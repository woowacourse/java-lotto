package util;

import java.util.List;

public class InputValidator {
    private InputValidator() {
    }

    public static void validateElements(List<String> input) {
        input.forEach(element -> {
            try {
                validateInteger(element);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(",로 구분된 숫자를 입력해주세요.");
            }
        });
    }

    public static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }
}
