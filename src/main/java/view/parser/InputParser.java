package view.parser;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public static final String DELIMITER = ",";

    public static List<Integer> parseWinningLotto(String inputWinningLotto) {
        try {
            return Arrays.stream(inputWinningLotto.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(toList());
        } catch (NumberFormatException e) {
            throw new  IllegalArgumentException(ParserExceptionMessage.INVALID_FORMAT);
        }
    }
}
