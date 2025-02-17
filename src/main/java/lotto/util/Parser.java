package lotto.util;

import java.util.List;

public class Parser {

    private static final String DELIMITER = ",";

    public static int parseToNumber(String money, String message) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    public static List<String> splitByComma(String input) {
        List<String> winningNumbers = List.of(input
                .replaceAll(" ", "")
                .split(DELIMITER));
        return winningNumbers;
    }
}
