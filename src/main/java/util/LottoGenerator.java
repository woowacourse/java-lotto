package util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int EXTRA_BOUND = 1;
    private static final int LOTTO_BALLS_IN_LOTTO = 6;

    private LottoGenerator() {
    }

    public static List<Integer> createRandomLottoNumber() {
        IntStream intStream = new Random().ints(MIN_NUMBER, MAX_NUMBER + EXTRA_BOUND);
        return intStream.distinct()
                .limit(LOTTO_BALLS_IN_LOTTO)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }
}