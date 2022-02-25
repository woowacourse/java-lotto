package utils;

import java.util.ArrayList;
import java.util.List;

public class Separator {

    private static final String REGEX = ", ";

    public static List<Integer> splitStringToListInt(String input) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : input.split(REGEX)) {
            numbers.add(Integer.parseInt(number));
        }
        return numbers;
    }
}
