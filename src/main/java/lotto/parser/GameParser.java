package lotto.parser;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class GameParser {

    private static final String NOT_NUMBER_EXCEPTION = "숫자만 입력 가능합니다.";
    private static final String DELIMITER = ",";

    public Set<Integer> parseInputToNumbers(String winningLottoInput) {
        return Arrays.stream(winningLottoInput.split(DELIMITER))
                .map(String::trim)
                .map(this::parseInputToInt)
                .collect(Collectors.toSet());
    }

    public int parseInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ne) {
            throw new IllegalArgumentException(NOT_NUMBER_EXCEPTION);
        }
    }
}
