package util;

import java.util.ArrayList;
import java.util.List;

public class InputConverter {

    public static int convertToInteger(String input) {
        try {
            int value = Integer.parseInt(input);
            if(value < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    public static List<Integer> convertToList(String input) {
        String[] rawNumbers = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for(String rawNumber : rawNumbers) {
            int number = convertToInteger(rawNumber);
            numbers.add(number);
        }
        return numbers;
    }
}
