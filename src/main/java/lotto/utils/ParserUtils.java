package lotto.utils;

import lotto.exception.IllegalTypeException;

public class ParserUtils {

    public static int tryParseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalTypeException(input);
        }
    }
}
