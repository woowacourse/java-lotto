package lotto.view.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputCheck {
    public static List<Integer> parseIntegerList(String text) {
        List<String> list = Arrays.asList(text.split(","));
        List<Integer> numbers = new ArrayList<>();
        for (String value : list) {
            numbers.add(parseInteger(value));
        }
        return numbers;
    }

    private static int parseInteger(String value) throws NumberFormatException {
        return Integer.parseInt(value);
    }
}
