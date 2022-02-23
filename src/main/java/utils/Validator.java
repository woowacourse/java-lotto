package utils;

public class Validator {
    public static void checkNullOrEmpty(final String inputNumber) {
        if (inputNumber == null || inputNumber.isEmpty()) {
            throw new IllegalArgumentException("null 또는 빈값을 입력할 수 없습니다.");
        }
    }

    public static void checkFormat(final String input) {
        if (!input.matches("^[1-9]([0-9]*)$")) {
            throw new IllegalArgumentException("입력한 값이 숫자의 형태가 아닙니다.");
        }
    }
}
