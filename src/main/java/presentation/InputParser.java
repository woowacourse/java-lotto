package presentation;

import exception.IllegalPositiveIntegerException;
import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static int parseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalPositiveIntegerException();
        }
    }

    public static List<Integer> parseNumbers(String text) {
        try {
            List<String> splittedText = Arrays.stream(text.split(",")).toList();
            return splittedText.stream().map(Integer::parseInt).toList();
        } catch (NumberFormatException exception) {
            throw new IllegalPositiveIntegerException();
        }
    }

}
