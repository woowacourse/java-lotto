package domain.generator;

import java.util.List;
import java.util.Random;

public class RandomGenerator implements Generator {

    @Override
    public List<Integer> generate() {
        return new Random()
                .ints(1, 45 + 1)
                .distinct()
                .limit(6)
                .boxed()
                .sorted()
                .toList();
    }
}
