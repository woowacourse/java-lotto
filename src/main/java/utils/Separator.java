package utils;

import java.util.ArrayList;
import java.util.List;

public class Separator {
    public static List<Integer> separateNumbers(String numbersText) {
        List<Integer> nums = new ArrayList<>();
        for (String s : numbersText.split(", ")) {
            nums.add(Integer.parseInt(s));
        }
        return nums;
    }
}
