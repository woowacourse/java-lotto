package controller;

import java.util.Arrays;
import java.util.List;
import message.ErrorMessage;

public class InputParser {
    public static int parseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_FORMAT.getMessage());
        }
    }

    public static List<Integer> parseNumbers(String text) {
        try {
            List<String> splittedText = Arrays.stream(text.split(",")).toList();
            return splittedText.stream().map(Integer::parseInt).toList();
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_FORMAT.getMessage());
        }
    }

}
