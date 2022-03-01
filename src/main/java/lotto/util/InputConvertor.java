package lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputConvertor {

    public static int toInt(String input) {
        InputValidator.checkInteger(input);
        return Integer.parseInt(input);
    }

    public static List<Integer> toInt(List<String> inputs) {
        return inputs.stream()
                .map(InputConvertor::toInt)
                .collect(Collectors.toList());
    }

    public static int toNaturalNumber(String input) {
        int inputInt = toInt(input);
        InputValidator.checkNaturalNumber(inputInt);
        return inputInt;
    }

    public static List<String> splitInput(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
