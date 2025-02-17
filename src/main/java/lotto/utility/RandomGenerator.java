package lotto.utility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomGenerator {

    public List<Integer> generateNumbers(int maxNumber, int count) {
        Set<Integer> randoms = new HashSet<>();

        while (randoms.size() < count) {
            int nextRandomNumber = (int) Math.ceil(Math.random() * maxNumber);
            randoms.add(nextRandomNumber);
        }

        return randoms.stream()
                .sorted()
                .toList();
    }
}
