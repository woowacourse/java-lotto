package lotto.utils;

import static lotto.exception.ErrorMessage.INVALID_INPUT;

import java.util.List;
import lotto.exception.LottoException;

public class Parser {

    public static int parseToInteger(String input) {

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new LottoException(INVALID_INPUT);
        }
    }

    public static List<Integer> parseToIntegers(List<String> input) {
        try {
            return input.stream().map(Parser::parseToInteger).toList();
        } catch (NumberFormatException e) {
            throw new LottoException(INVALID_INPUT);
        }
    }
}
