package common;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NumberGenerator {

    public static List<Integer> generate(int size, int minNumber, int maxNumber) {
        return new Random()
                .ints(minNumber, maxNumber + 1)
                .distinct()
                .limit(size)
                .boxed()
                .collect(Collectors.toList());
    }
}
