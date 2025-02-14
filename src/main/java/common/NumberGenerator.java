package common;

import java.util.List;
import java.util.Random;

public class NumberGenerator {

    public static List<Integer> generate(int size, int minNumber, int maxNumber) {
        return new Random()
                .ints(minNumber, maxNumber + 1)
                .distinct()
                .limit(size)
                .boxed()
                .toList();
    }
}
