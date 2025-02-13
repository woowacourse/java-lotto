package lotto.utils;

import java.util.List;

public class Parser {

    public static int parseToInteger(String input) {

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 입력값 입니다.");
        }
    }

    public static List<Integer> parseToIntegers(List<String> input) {
        try {
            return input.stream().map(Parser::parseToInteger).toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 입력값 입니다.");
        }
    }
}
