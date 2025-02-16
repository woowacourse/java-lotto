package lotto.util;

public class Parser {
    public static int validateNumber(String money, String message) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

}
