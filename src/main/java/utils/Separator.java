package utils;

import java.util.ArrayList;
import java.util.List;

public class Separator {

    public static List<Integer> splitStringToListInt(final String input, final String regex) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : input.split(regex)) {
            numbers.add(Integer.parseInt(number));
        }
        return numbers;
    }
}
