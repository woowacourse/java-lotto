package lotto.utils;

import lotto.exceptions.NotNumberException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberParser {
    private static final String DELIMITER = ",";
    private static final String NOT_NUMBER_MESSAGE = "숫자만 입력하세요.";

    public static List<Integer> winningNumberParse(String input) {
        return Stream.of(input.split(DELIMITER))
                .map(String::trim)
                .map(NumberParser::parseNumber)
                .collect(Collectors.toList());
    }

    public static int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(NOT_NUMBER_MESSAGE);
        }
    }
}
