package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NumberGenerator {
    public static List<Integer> pickUniqueRandomNumbers(int min, int max, int count) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while(true) {
            set.add(random.nextInt(max) + min);
            if(set.size() == count) {
                break;
            }
        }
        for(int number : set) {
            numbers.add(number);
        }
        return numbers;
    }
}
