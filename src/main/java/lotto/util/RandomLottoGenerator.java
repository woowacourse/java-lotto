package lotto.util;

import lotto.model.Lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator {

    public static final int LOTTO_START_NUMBER = 1;
    public static final int LOTTO_LAST_NUMBER = 45;
    public static final int LOTTO_START_INDEX = 0;

    private static final List<Integer> NUMBER_RANGE = IntStream.rangeClosed(LOTTO_START_NUMBER, LOTTO_LAST_NUMBER)
            .boxed()
            .collect(Collectors.toList());

    public static Lotto generate() {
        Collections.shuffle(NUMBER_RANGE);
        return new Lotto(NUMBER_RANGE.subList(LOTTO_START_INDEX, 6));
    }
}
