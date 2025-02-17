package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class NumberGeneratorImpl implements NumberGenerator {
    public List<Integer> pickUniqueRandomNumbers(int min, int max, int count) {
        Random random = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < count) {
            uniqueNumbers.add(random.nextInt(max) + min);
        }
        return new ArrayList<>(uniqueNumbers);
    }
}
