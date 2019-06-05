package util;

public class NonNegativeIntegerParse {
    public static int parse(String input) {
        try {
            return Integer.parseUnsignedInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("음아닌 정수를 입력해주세요.");
        }
    }
}
