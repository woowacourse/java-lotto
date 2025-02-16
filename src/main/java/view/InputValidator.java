package view;

public class InputValidator {
    public static void validateBlank(String value) {
        if (value.isBlank() || value == null) {
            throw new IllegalArgumentException("빈 값이 아닌 값을 입력해주세요.");
        }
    }

    public static void validateIntegerOverflow(String value) {
        if (value.length() > 10) {
            throw new IllegalArgumentException("10자리 이하의 정수를 입력해주세요.");
        }
    }
}
