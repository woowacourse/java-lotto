package lotto.parser;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class GameParser {

    private static final String DELIMITER = ",";

    public static Set<Integer> parseInputToNumbers(String winningLottoInput) {
        return Arrays.stream(winningLottoInput.split(DELIMITER))
                .map(String::trim)
                .map(GameParser::parseInputToInt)
                .collect(Collectors.toSet());
    }

    public static int parseInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ne) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }
}
