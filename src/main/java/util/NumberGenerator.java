package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberGenerator {

    public static List<Integer> pickUniqueNumbersInRange(int min, int max, int count, NumberPickStrategy numberPickStrategy) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < count) {
            uniqueNumbers.add(numberPickStrategy.pickInRange(min, max));
        }
        return new ArrayList<>(uniqueNumbers);
    }
}
