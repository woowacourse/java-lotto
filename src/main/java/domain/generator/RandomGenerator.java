package domain.generator;

import static domain.Lotto.LOTTO_LENGTH;
import static domain.Lotto.LOTTO_MAX;
import static domain.Lotto.LOTTO_MIN;

import java.util.List;
import java.util.Random;

public class RandomGenerator implements Generator {

    @Override
    public List<Integer> generate() {
        return new Random()
                .ints(LOTTO_MIN, LOTTO_MAX + 1)
                .distinct()
                .limit(LOTTO_LENGTH)
                .boxed()
                .sorted()
                .toList();
    }
}
