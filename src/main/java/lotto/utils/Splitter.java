package lotto.utils;

import static lotto.exception.ExceptionMessage.INVALID_INPUT;

import java.util.List;
import lotto.exception.LottoException;

public class Splitter {

    private static final String COMMA_SPLIT_REGEX = "\\s*\\d+\\s*(,\\s*\\d+\\s*)*";

    public static List<String> splitByComma(String input) {
        try {
            if (!input.matches(COMMA_SPLIT_REGEX)) {
                throw new LottoException(INVALID_INPUT);
            }
            return List.of(input.split(","));
        } catch (NumberFormatException e) {
            throw new LottoException(INVALID_INPUT);
        }
    }
}
