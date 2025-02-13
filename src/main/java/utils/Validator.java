package utils;

public class Validator { // todo : 네이밍 고민 체크, 에러 메시지 추가

    public static void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateRange(int input, int min, int max) {
        if (input > max || input < min) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateDivide(int input, int division) { // todo : 네이밍 고민 체크
        if (input % division != 0) {
            throw new IllegalArgumentException();
        }
    }
}
