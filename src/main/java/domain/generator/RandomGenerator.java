package domain.generator;

import static domain.Lotto.LOTTO_LENGTH;
import static domain.LottoNumber.LOTTO_MAX;
import static domain.LottoNumber.LOTTO_MIN;

import java.util.List;
import java.util.Random;

public class RandomGenerator implements Generator {

    private static final Random RANDOM = new Random();

    @Override
    public List<Integer> generate() {
        return RANDOM
                .ints(LOTTO_MIN, LOTTO_MAX + 1)
                .distinct()
                .limit(LOTTO_LENGTH)
                .boxed()
                .sorted()
                .toList();
    }
}
