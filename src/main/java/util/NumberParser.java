package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberParser {
    public static int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    public static List<Integer> parseFromCSV(String input) {
        final List<Integer> parsedNumbers = new ArrayList<>();
        final List<String> inputNumbers = Arrays.stream(input.split(",")).map(String::trim).toList();
        inputNumbers.forEach(element -> {
            try {
                parsedNumbers.add(parse(element));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("쉼표(,)로 구분된 숫자를 입력해주세요.");
            }
        });
        return parsedNumbers;
    }
}
