package utils;

import java.util.ArrayList;
import java.util.List;

public class Separator {

    public static final String REGEX = ", ";

    public static List<Integer> separateNumbers(String numbersText) {
        List<Integer> nums = new ArrayList<>();
        for (String s : numbersText.split(REGEX)) {
            nums.add(Integer.parseInt(s));
        }
        return nums;
    }
}
