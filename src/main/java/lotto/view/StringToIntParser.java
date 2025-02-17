package lotto.view;

import java.util.Arrays;
import java.util.List;

public class StringToIntParser {

    public static int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("입력값은 21억 이하의 양수여야 합니다.");
        }
    }

    public static List<Integer> parseTokens(final String[] tokens) {
        return Arrays.stream(tokens)
                .map(String::trim)
                .map(StringToIntParser::parseInt)
                .toList();
    }
}
