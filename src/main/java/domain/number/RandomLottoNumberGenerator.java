package domain.number;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    private final int EXTRA_BOUND = 1;

    @Override
    public List<List<Integer>> createBundle(final int quantity) {
        final List<List<Integer>> numberBundle = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            numberBundle.add(create());
        }
        return numberBundle;
    }

    @Override
    public List<Integer> create() {
        final IntStream intStream = new Random().ints(MIN_NUMBER, MAX_NUMBER + EXTRA_BOUND);
        return intStream.distinct()
                .limit(LOTTO_BALLS_IN_LOTTO)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }
}